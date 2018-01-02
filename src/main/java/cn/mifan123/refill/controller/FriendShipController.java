package cn.mifan123.refill.controller;


import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value= "好友", description="好友相关API")
@RestController
@RequestMapping(value = "friendships", produces = MediaType.APPLICATION_JSON_VALUE)
public class FriendShipController {
}
