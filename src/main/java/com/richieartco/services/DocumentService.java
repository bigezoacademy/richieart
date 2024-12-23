package com.richieartco.services;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {
    void uploadDocuments(MultipartFile doc1, MultipartFile doc2, MultipartFile doc3, MultipartFile doc4);


}
