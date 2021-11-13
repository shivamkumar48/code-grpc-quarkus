package com.shivam;

import grpc.health.v1.HealthGrpc;
import grpc.health.v1.HealthOuterClass;
import io.quarkus.grpc.GrpcClient;
import io.quarkus.grpc.GrpcService;

import io.smallrye.mutiny.Uni;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/grpc/client")
public class HelloGrpcService{

    @GrpcClient("helloGrpc")
    HelloGrpcGrpc.HelloGrpcBlockingStub helloClient;

    @GrpcClient("health")
    HealthGrpc.HealthBlockingStub healthClient;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{name}")
    public Response getGreetingFromGrpcServer(@PathParam("name") String name){

        HealthOuterClass.HealthCheckResponse healthCheckResponse = healthClient.check(HealthOuterClass.HealthCheckRequest.newBuilder().setService("com.shivam.HelloGrpc").build());
        System.out.println(healthCheckResponse.getStatus().name());

        HelloReply reply = helloClient.sayHello(HelloRequest.newBuilder().setName(name).build());
        System.out.println("received "+ reply.getMessage());
        return Response.ok(new Person(name, reply.getMessage())).build();
    }

}
