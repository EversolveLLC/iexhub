package org.iexhub.services.dto;

/**
 * Used by  org.iexhub.services.GetPatientDataService 
 * Created by tomson.ngassa on 7/11/2016.
 */
@Deprecated
public class ClinicalDocumentRequest {
    private byte[] document;

    public ClinicalDocumentRequest() {
    }

    public ClinicalDocumentRequest(byte[] document) {
        this.document = document;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }
}
