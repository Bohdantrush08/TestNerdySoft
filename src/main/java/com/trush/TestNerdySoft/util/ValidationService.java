package com.trush.TestNerdySoft.util;

import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public boolean allExist(String ...args) {
        for (String arg: args) {
            if (arg == null || arg.isEmpty())
                return false;
        }
        return true;
    }
}
