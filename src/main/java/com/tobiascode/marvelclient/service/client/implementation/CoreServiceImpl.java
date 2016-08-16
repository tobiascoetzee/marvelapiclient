package com.tobiascode.marvelclient.service.client.implementation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tobiascode.marvelclient.rest.client.ApiClient;
import com.tobiascode.marvelclient.rest.constants.Parameter;
import com.tobiascode.marvelclient.rest.util.CommonParameters;
import com.tobiascode.marvelclient.service.constants.Property;
import com.tobiascode.marvelclient.service.model.Backup;
import com.tobiascode.marvelclient.service.model.containsresources.ContainsOtherResources;
import com.tobiascode.marvelclient.util.BackupRestore;
import com.tobiascode.marvelclient.util.Configuration;

public abstract class CoreServiceImpl<S extends Serializable, A, W> {
    private final Logger logger = LoggerFactory.getLogger(CoreServiceImpl.class);
    private final ApiClient<W> apiClient;
    private final Class wrapperType;
    private Function<W, Integer> getTotalResources;
    private Function<W, List<A>> getResults;
    private Function<A, S> mapApiResourceToServiceResource;
    private Function<A, Integer> getApiResourceId;
    private String resourceUrl;
    private String defaultOrderBy;
    private String defaultSearchBy;
    private int maximumLimit;
    private boolean keepBackup;
    private String backupPath;

    protected CoreServiceImpl(Class wrapperType) {
        apiClient = new ApiClient<>();
        this.wrapperType = wrapperType;
        defaultOrderBy = Parameter.DEFAULT_ORDER_BY;
        defaultSearchBy = Parameter.DEFAULT_SEARCH_BY;
        maximumLimit = Integer.valueOf(Configuration.getProperty(Property.MAXIMUM_LIMIT).orElse("100"));
        keepBackup = Boolean.valueOf(Configuration.getProperty(Property.KEEP_BACKUP).orElse("false"));
        backupPath = Configuration.getProperty(Property.BACKUP_FOLDER).orElse("./");
    }

    protected void setGetTotal(Function<W, Integer> getTotal) {
        this.getTotalResources = getTotal;
    }

    protected void setGetResults(Function<W, List<A>> getResults) {
        this.getResults = getResults;
    }

    protected void setMapApiResourceToServiceResource(Function<A, S> mapApiResourceToServiceResource) {
        this.mapApiResourceToServiceResource = mapApiResourceToServiceResource;
    }

    protected void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    protected void setGetApiResourceId(Function<A, Integer> getApiResourceId) {
        this.getApiResourceId = getApiResourceId;
    }

    protected void setDefaultOrderBy(String defaultOrderBy) {
        this.defaultOrderBy = defaultOrderBy;
    }

    public int getTotalResources() {
        Optional<W> dataWrapper = apiClient.get(resourceUrl, new CommonParameters().getSingleLimitParameter(), wrapperType);

        if (dataWrapper.isPresent()) {
            return getTotalResources.apply(dataWrapper.get());
        }

        return 0;
    }

    public List<S> getAllResources(int totalResources) {
        int offset = 0;
        List<S> allResources = new ArrayList<>();
        BackupRestore backupRestore = new BackupRestore(keepBackup, buildBackupFilePath("getAllResources"));

        if (keepBackup) {

            Optional<Backup> backup = backupRestore.readBackup();

            if (backup.isPresent()) {
                offset = backup.get().getLastOffset();
                offset = Integer.valueOf(Configuration.getProperty(Property.START_OFFSET).orElse(String.valueOf(offset)));

                allResources = (List<S>) backup.get().getItems();
                logger.info("Restoring backup with offset at: " + offset);
            }
        }

        while (offset < totalResources) {
            List<S> newResources = getResourcesFromApi(offset, totalResources);

            if (newResources.isEmpty()) {
//                throw new ReadingMarvelResourceException("Error getting data from " + resourceUrl, offset, maximumLimit);
            }

            allResources.addAll(newResources);
            offset = offset + maximumLimit;

            backupRestore.writeBackup(offset, allResources);
        }

        return allResources;
    }

    public void getResourceIdsForGivenResources(List<? extends ContainsOtherResources> resources) {
        if (resources == null || resources.isEmpty()) {
            return;
        }

        int offset = 1;

        String originalTypeName = resources.get(0).getOriginalResourceName();
        BackupRestore backupRestore = new BackupRestore(keepBackup, buildBackupFilePath("getResourceIdsForGivenResources_" + originalTypeName));
        restorePreviousResourceIdState(resources, backupRestore);

        for (ContainsOtherResources resource : resources) {
            if (resource.getResourceIds().isEmpty() && resource.getAvailableResource() > 0) {
                logger.info("Getting [" + resource.getAvailableResource() + "] " + resource.getThisResourceName() + "Ids for: " + resource.getContainingResourceName());

                List<Integer> eventIds = getAllResourceIdsForResource(resource.getResourceCollectionUri(), resource.getAvailableResource());
                resource.setResourceIds(eventIds);

                backupRestore.writeBackup(offset, resources);
            } else {
                logger.info("Skipping getting [" + resource.getAvailableResource() + "] " + resource.getThisResourceName() + "Ids for: " + resource.getContainingResourceName());
            }

            offset++;
        }
    }

    private void restorePreviousResourceIdState(List<? extends ContainsOtherResources> resources, BackupRestore backupRestore) {
        if (keepBackup) {
            Optional<Backup> backup = backupRestore.readBackup();

            if (backup.isPresent()) {
                List<? extends ContainsOtherResources> restoredResources = (List<? extends ContainsOtherResources>) backup.get().getItems();

                for (ContainsOtherResources currentResource : resources) {
                    ContainsOtherResources restoredResource = restoredResources.stream().filter(r -> r.getResourceCollectionUri().equals(currentResource.getResourceCollectionUri())).findFirst().get();
                    currentResource.setResourceIds(restoredResource.getResourceIds());
                }
            }
        }
    }

    public List<S> searchFor(String searchValue) {
        List<S> newResources = new ArrayList<>();

        Optional<W> dataWrapper = apiClient.get(resourceUrl, new HashMap<String, Object>() {{
            put(defaultSearchBy, searchValue);
        }}, wrapperType);

        if (dataWrapper.isPresent()) {
            newResources.addAll(getResults.apply(dataWrapper.get())
                    .stream()
                    .map(mapApiResourceToServiceResource)
                    .collect(Collectors.toList()));
        }

        return newResources;
    }

    private List<S> getResourcesFromApi(int offset, int limit) {
        List<S> newResources = new ArrayList<>();
        Optional<W> dataWrapper = apiClient.get(resourceUrl, new CommonParameters(offset, limit, defaultOrderBy).getCommonThreeParameters(), wrapperType);

        if (dataWrapper.isPresent()) {
            newResources.addAll(getResults.apply(dataWrapper.get())
                    .stream()
                    .map(mapApiResourceToServiceResource)
                    .collect(Collectors.toList()));
        }

        return newResources;
    }

    private List<Integer> getAllResourceIdsForResource(String collectionURI, int totalResources) {
        int offset = 0;
        List<Integer> resourceIds = new ArrayList<>();

        while (offset < totalResources) {
            List<Integer> newResourceIds = getAllResourceIdsForResourceFromApi(collectionURI, offset);
            resourceIds.addAll(newResourceIds);

            offset = offset + maximumLimit;
            logger.info("Fetched [" + (offset > totalResources ? totalResources : offset) + "] resources out of [" + totalResources + "] from: " + collectionURI);
        }

        return resourceIds;
    }

    private List<Integer> getAllResourceIdsForResourceFromApi(String collectionURI, int offset) {
        List<Integer> resourceIds = new ArrayList<>();

        Optional<W> dataWrapper = apiClient.get(collectionURI, new CommonParameters(offset, defaultOrderBy).getCommonThreeParameters(), wrapperType);

        if (dataWrapper.isPresent()) {
            resourceIds.addAll(getResults.apply(dataWrapper.get())
                    .stream()
                    .map(getApiResourceId)
                    .collect(Collectors.toList()));
        }

        return resourceIds;
    }

    private String buildBackupFilePath(String methodName) {
        return backupPath + wrapperType.getSimpleName() + "_" + methodName + ".bck";
    }
}
