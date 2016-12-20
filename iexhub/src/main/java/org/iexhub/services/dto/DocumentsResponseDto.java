package org.iexhub.services.dto;
import java.util.ArrayList;

/**
 * Used by  org.iexhub.services.GetPatientDataService 
 * Created by tomson.ngassa on 7/11/2016.
 */


@Deprecated
public class DocumentsResponseDto {

    private ArrayList<PatientDocument> documents;

    public ArrayList<PatientDocument> getDocuments() {
        return this.documents;
    }

    public void setDocuments(ArrayList<PatientDocument> documents) {
        this.documents = documents;
    }
}
