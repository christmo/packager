/*
 * ================================ Xplook Render ajax filter====================================
 */
window.addEventListener('load', load_sucess, load_failed);
var outputMesages = true;
var restEndPoiny = 'Services/JSONEndPoint';

function load_sucess(){
  $(document).ajaxSend(function (event, request, settings){
    xplook.core.processAjax(settings, request);
  });
  /*$(document).ajaxError(function(event, request, settings) {
    xplook.console( "<li>Error requesting page " + settings.url + "</li>");
    xplook.console(event);
    xplook.console(request);
  });*/
  xplook.console('-----------------------'+xplook.version+' is Ready!!-------------------------------');
  
}

function load_failed(){
  console.debug('xplook render load failed...');
}


/*
 * ================================ Xplook render ====================================
 */
var xplook_core ={
  processAjax : function(data,request){
    xplook.console('==========================xplookRender process=======================================');
    xplook.console('mmmm this is a '+data.type+' ajax!');
    if(data.type=='GET'){
      this.processGet(data,request);
    }else{
      if(data.type=='POST'){
        this.processPost(data,request)
      }
    }
  },
  processGet : function(data,request){
    //console.debug('porcesing get....');
    if(data.hasOwnProperty('xplook_packet')){      
      xplook.console('we got a xplookPacket!!');
      xplook.console('stopping common ajax....');
      request.abort();
      this.processXplookPacket(data);
    }else{
      if(data.url.hasOwnProperty('xplook_packet')){        
        xplook.console('we got a xplookPacket!!');
        xplook.console('stopping common ajax....');
        request.abort();
        this.processXplookPacket(data.url);
      }else{
        xplook.console('just a common ajax....');
      }
    }
  },
  processPost : function(data,request){
    if(data.url.hasOwnProperty('xplook_packet')){
      xplook.console('we got a xplookPacket!!');
      xplook.console('stopping common ajax....');
      request.abort();
      this.processXplookPacket(data.url);
    }else{
      xplook.console('xplookRenderSays:just a common ajax....');
    }
  },
  processXplookPacket : function(xplook_packet){
    xplook.console(xplook_packet);
    xplook.console('creating your packet.... whats new with nicks?? :P');
    $.get(restEndPoiny,xplook_packet,sucessAjaxXplook);
  },
  print:function (xplook_packet){
    if(outputMesages){
      xplook.console("------------------Xplook packet printer--------------------");
      xplook.console(xplook_packet);
    }   
  }
  
};

function sucessAjaxXplook(result, status)
{
  alert(status);
  xplook.console(result);
              
}
var xplook ={
  version:" Xplook Render - 1.0",
  core:xplook_core, 
  processPacket:function(xplook_packet){    
    this.core.processXplookPacket(xplook_packet);    
  }, 
  console:function(data){
    if(outputMesages){
      console.debug('XplookRender says:');
      console.debug(data);
      $('#text_area_console').append('<p>XplookRender says:'+data+'</p>');
    }
  }
    
};

/*
 * ================================ Xplook packet builder ====================================
 */

var xplookBuilder={
  newPacket:function(){    
    var packet = new Object();
    packet.xplook_packet={
      header:{
        idUser:"user",
        mode:"",
        actionType:"",
        objectType:""
      },
      packetData:{
        id:"",
        database:"",
        collection:"",
        request:{},
        response:{}
      },
      error:[]
    };
    return  packet;
  },
  newErrorItem:function(){  
    var error_item = new Object();
    error_item.errorCode="",
    error_item.description="",
    error_item.errorType="",
    error_item.parameters=[];
    return error_item;

  }
}

/*
 * ================================ Xplook utils ====================================
 */

