package com.spring.practice.my.app.post.adapter;

record PostRequest(String title,
        String content,
        int level,
        boolean active) {
}
