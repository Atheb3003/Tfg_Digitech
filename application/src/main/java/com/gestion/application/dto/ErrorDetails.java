package com.gestion.application.dto;

public record ErrorDetails(int status, String error, String message, String path) {}
