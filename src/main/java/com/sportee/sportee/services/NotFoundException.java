package com.sportee.sportee.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "user title not found")
public class NotFoundException extends RuntimeException {


}
