# RetrofitUtils

封装主要的目的就是减少相同代码的编写，分离模块，集中处理。retrofit2在请求一个url的时候，每次需要重写一个新的注解api，同时相关联的几个文件也需要修改。这无疑增加了出错概率，同时代码非常冗余，不利于以后的集中维护。基于这几点，需要将retrofit2再次封装。

1、BaseResponseT。基本的返回结构，其中返回的具体数据内容作为一个JsonObject来处理。 2、RequestSpecificApi。需要参数@Url，可以是完整url或者需要base url的部分url。Call不能为Call，retrofit内部不允许此处调用的类型不确定。 3、RetrofitUtil。mCalls保存所有未执行的请求，提供add、remove和cancelAllCalls。 4、RequestServer。调用请求与Retrofit请求的中间连接文件。getFreeMissions（名字可以随便改）的参数args必须是成对出现，因为要组成提交服务器的key-value数据组。getFreeMissions2则可以自己添加修改（不建议使用），这个方法可能每个请求的url都要一个对应的，会很多。而getFreeMissions则只需要按照方法规则调用就行，可以对应所有的请求url。 5、ResponseResult。我只提供了一个onResponse回调方法，用status参数来区别是请求成功还是失败。请求成功，则data就是返回的结构。失败，msg就是要展示的失败原因。
