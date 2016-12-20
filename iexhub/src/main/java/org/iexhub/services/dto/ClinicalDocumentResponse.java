package org.iexhub.services.dto;

/**
 * Used by  org.iexhub.services.GetPatientDataService 
 * Created by tomson.ngassa on 7/11/2016.
 */
@Deprecated
public class ClinicalDocumentResponse {
    private boolean published;

    public ClinicalDocumentResponse() {
    }

    public ClinicalDocumentResponse(boolean published) {
        this.published = published;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
