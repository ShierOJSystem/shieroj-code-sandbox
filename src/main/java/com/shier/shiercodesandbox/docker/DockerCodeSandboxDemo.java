package com.shier.shiercodesandbox.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.*;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.LogContainerResultCallback;

import java.util.List;

/**
 * @author Shier 2023/9/2 17:55
 */
public class DockerCodeSandboxDemo {
    public static void main(String[] args) throws InterruptedException {
        DockerClient dockerClient = DockerClientBuilder.getInstance().build();
        // 1、拉取镜像
        String image = "nginx:latest";
//        PullImageCmd pullImageCmd = dockerClient.pullImageCmd(image);
//        PullImageResultCallback resultCallback = new PullImageResultCallback() {
//            @Override
//            public void onNext(PullResponseItem item) {
//                System.out.println("拉取镜像：" + item.getStatus());
//                super.onNext(item);
//            }
//        };
//        pullImageCmd.exec(resultCallback).awaitCompletion();
//        System.out.println("拉取完成");
        // 2、创建容器
        CreateContainerCmd containerCmd = dockerClient.createContainerCmd(image);
        CreateContainerResponse createContainerResponse = containerCmd.withCmd("echo", "Hello Nginx").exec();
        String containerId = createContainerResponse.getId();
        System.out.println("容器id：" + containerId);

        // 3、查看容器状态
        ListContainersCmd listContainersCmd = dockerClient.listContainersCmd();
        List<Container> containerList = listContainersCmd.withShowAll(true).exec();
        for (Container container : containerList) {
            System.out.println("存在容器：" + container.getId());
        }
        // 4、启动容器
        dockerClient.startContainerCmd(containerId).exec();

        // 5、查看启动容器日志
        LogContainerResultCallback resultCallback = new LogContainerResultCallback() {
            @Override
            public void onNext(Frame item) {
                System.out.println(containerId + " 此容器日志:" + new String(item.getPayload()));
                super.onNext(item);
            }
        };
        dockerClient.logContainerCmd(containerId)
                .withStdErr(true) // 错误输出
                .withStdOut(true) // 标准输出
                .exec(resultCallback)
                .awaitCompletion(); // 异步操作

        // 6、删除容器
        dockerClient.removeContainerCmd(containerId)
                .withForce(true) // 强制删除
                .exec();

        // 删除所有容器
        for (Container container : containerList) {
            if (container.getId() != null) {
                dockerClient.removeContainerCmd(container.getId())
                        .withForce(true) // 强制删除
                        .exec();
            }
        }

        // 7、删除镜像
        dockerClient.removeImageCmd(image).exec();
    }
}
