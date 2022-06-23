package com.service.shortLink.service.codeGenerator;

import org.apache.commons.text.RandomStringGenerator;

public class CodeGenerator {
    private RandomStringGenerator randomStringGenerator;

    public CodeGenerator() {
        this.randomStringGenerator = new RandomStringGenerator
                .Builder()
                .withinRange('0', 'z')
                .build();
    }

    public String generate(int length) {
        return randomStringGenerator.generate(length);
    }
}
