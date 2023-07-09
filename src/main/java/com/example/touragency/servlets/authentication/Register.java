package com.example.touragency.servlets.authentication;

import jakarta.servlet.http.HttpServlet;

// Browsers can submit form data only through HTTP GET or HTTP POST
// but non-browser clients can also use HTTP PUT, PATCH, and
// DELETE. The Servlet API requires ServletRequest.getParameter*()
// methods to support form field access only for HTTP POST.
public class Register extends HttpServlet {
}
