package com.tobiascode.marvelclient.service.model.containsresources;

import java.io.Serializable;
import java.util.List;

public interface ContainsOtherResources extends Serializable{
    String getThisResourceName();

    String getContainingResourceName();

    String getOriginalResourceName();

    String getResourceCollectionUri();

    int getAvailableResource();

    void setResourceIds(List<Integer> resourceIds);

    List<Integer> getResourceIds();
}
