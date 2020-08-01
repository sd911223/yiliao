package com.platform.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PdfService {
    void exportPdf(String id, HttpServletRequest request, HttpServletResponse response);
}
