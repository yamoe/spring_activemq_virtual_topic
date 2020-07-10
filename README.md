# spring_activemq_virtual_topic

아래 글을 보고 ActiveMQ (AmazoneMQ) Virtual Topic 테스트

[ActiveMQ의 Virtual Destinations를 활용한 메시지 로드밸런싱](https://ryan-han.com/post/server/activemq_virtual_destinations/)

## 요약

ActiveMQ 의 Topic(pub-sub) 사용시 consume 서버를 이중화 화면 메시지를 중복 해서 받게 되어 처리가 필요 한데
Virtual Topic 을 사용 하면 pub-sub 도 되고 내부적으로 Queue 가 생겨 메시지를 하나씩 라운드 로빈해서도 받을 수 있다.

## ActiveMQ 설정 변경

Apache ActiveMQ 5.15.9 에서 테스트

아래와 같이 설정 후 AmazonMQ 재시작

* useVirtualTopics="true" 추가
* \<destinationInterceptors\> 추가

```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<broker schedulePeriodForDestinationPurge="10000" useVirtualTopics="true" xmlns="http://activemq.apache.org/schema/core">
  <destinationInterceptors>
    <mirroredQueue copyMessage="true" postfix=".qmirror" prefix=""/>
    <virtualDestinationInterceptor>
      <virtualDestinations>
        <virtualTopic name="VirtualTopic.&gt;" prefix="Consumers.*." selectorAware="false"/>
      </virtualDestinations>
    </virtualDestinationInterceptor>
  </destinationInterceptors>
```

VirtualTopic 의 Queue 를 사용할 container factory 는 "factory.setPubSubDomain(false)" 설정이 되어야 한다.


## 테스트

1. -Dserver.port=8080, -Dserver.port=8081 로 서버 2대 띄우고
1. http://localhost:8080/test 호출 하여 "VirtualTopic.vMailbox" Topic 으로 producing
1. "VirtualTopic.vMailbox" JmsListener 는 매번 메시지를 받음을 확인
1. "Consumers.QQQ.VirtualTopic.vMailbox" JmsListener 는 서버 2개가 번갈아 가면서 메시지를 받음을 확인


