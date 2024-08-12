package com.invassiion.SaveFromVkVideoBot.controller;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.BatchV1Api;
import io.kubernetes.client.openapi.models.V1EnvVar;
import io.kubernetes.client.openapi.models.V1Job;
import io.kubernetes.client.util.Yaml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    private ApiClient apiClient;

    @PostMapping
    public String downloadVideo(@RequestParam String videoUrl) {
        BatchV1Api batchV1Api = new BatchV1Api(apiClient);

        V1Job job = loadJobFromYaml();

        job.getSpec().getTemplate().getSpec().getContainers().get(0).getEnv().add(
                new V1EnvVar().name("VIDEO_URL").value(videoUrl)
        );

        batchV1Api.createNamespacedJob("default" , job);
        return "Job started for video : " + videoUrl;

    }

    private V1Job loadJobFromYaml() {
        return null;
    }

}
