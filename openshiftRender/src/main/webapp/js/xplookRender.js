
(function() {
	//loading jquery base lib
	var fileJQ=document.createElement("script");	
	fileJQ.setAttribute("type", "text/javascript");
	fileJQ.setAttribute("charset", "utf-8");
	fileJQ.setAttribute("src", 'js/jquery-1.3.2.js');
	document.getElementsByTagName("head")[0].appendChild(fileJQ);

	//when jquery is loaded run xplook engine
	fileJQ.onload = function() {
		//xplook.loadTemplate(xplook.template);
		XPLOOK_CORE.startAjaxFilter();
		xplook.console('-----------------------' + xplook.version + ' is Ready!!-------------------------------');
		xplook.loaded = true;

		$( document ).ready(function() {
			xplook.loadTemplate(xplook.template);
			xplook.console( "document loaded" );
		});

		$( window ).load(function() {
			xplook.console( "window loaded" );
		});
	};


	xplook = {
			version: " Xplook Render - 1.0",
			template:undefined,
			splinner:true,
			loaded:false,
			restEndPoiny: 'Services/JSONEndPoint',
			controls:['top','foother','rightMenu'],
			outputMesages: true,			
			processEntity: function(entityAction) {
				var cholds = $(entityAction).parent().children();
				for (var i=0;i<cholds.length;i++){
					console.log(cholds[i]);
				}
			}
	};



	/*
	 * ================================ Template Loader ===================================
	 */

	xplook.loadTemplate = function(template){

		var fileref=document.createElement("link");
		fileref.setAttribute("rel", "stylesheet");
		fileref.setAttribute("type", "text/css");

		if(template!=undefined){
			fileref.setAttribute("href", 'css/'+template+'.css');
			document.getElementsByTagName("head")[0].appendChild(fileref);
			
			for(var i=0; i< xplook.controls.length; i++){					
				$('#'+xplook.controls[i]).load('boxes/w_'+template+'_'+xplook.controls[i]+'.html');
			}

		}else{
			fileref.setAttribute("href", 'css/xplookDefault.css');
			document.getElementsByTagName("head")[0].appendChild(fileref);

			for(var i=0; i< xplook.controls.length; i++){
				$('#'+xplook.controls[i]).load('boxes/w_xplookDefault_'+xplook.controls[i]+'.html');
			}
		}
		

	};

})();

var xpSplinner;

/*
 * ================================ Xplook Core====================================
 */	
xplook.core = function(){
	this.processAjax = function(data, request) {
		xplook.console('==============================xplookRender process=================================');
		xplook.console('------->$' + data.type + '()!!!');			

		if (data.type === 'GET') {
			this.processGet(data, request);
		} else {
			if (data.type === 'POST') {
				this.processPost(data, request);
			}
		}
	};

	this.processGet = function(data, request) {
		var xplookPacket = null;
		if (this.validateXplookPacket(data)) {
			xplookPacket = data;
		} else {
			if (this.validateXplookPacket(data.url)) {
				xplookPacket = data.url;
			}
		}

		if (xplookPacket !== null) {
			this.postToXplookServer(data, request, data.success);
		} else {
			if(xplook.splinner & xpSplinner!= undefined){
				xpSplinner.hide();
			}
			xplook.console('just a common ajax....');
		}
	};
	this.processPost = function(data, request) {
		if (this.validateXplookPacket(data.url)) {
			this.postToXplookServer(data.url, request, data.success);
		} else {
			if(xplook.splinner & xpSplinner!= undefined){
				xpSplinner.hide();
			}				
			xplook.console('just a common ajax....');
		}
	};
	this.validateXplookPacket =  function(xplook_Packet) {
		var xpBody = false;
		if (xplook_Packet.hasOwnProperty('xplookHeader')) {
			xpBody = true;
		} else {
			return false;
		}
		if (xplook_Packet.hasOwnProperty('xplookPacketData')) {
			xpBody = true;
		} else {
			return false;
		}
		if (xplook_Packet.hasOwnProperty('xplookError')) {
			xpBody = true;
		} else {
			return false;
		}
		return xpBody;
	};
	this.postToXplookServer =  function(xplook_packet, request, sucessFunction) {
		if(xplook.splinner){
			xpSplinner = new xplook.render.splinner();
			xpSplinner.show();
		}

		try {
			xplook.console('stopping common ajax....');
			if(request!=undefined){
				request.abort();
			}

		} catch (ex) {
			xplook.console(ex);
		}
		xplook.print(xplook_packet);
		var objXplook = new Object();
		objXplook.xplook_packet = xplook_packet;
		xplook.console('Sending to XPLOOK server...');
		if(sucessFunction != undefined){
			if(xplook.splinner & xpSplinner!= undefined){
				userFunction = (function() {
					var cached_function = sucessFunction;
					return function() {
						xpSplinner.hide();
						cached_function.apply(this, arguments); 
					};
				}());
			}else{
				userFunction = sucessFunction;
			}

			$.get(xplook.restEndPoiny, objXplook, userFunction);

		}else{
			$.get(xplook.restEndPoiny, objXplook, this.sucessAjaxXplook);
		}

	};
	this.startAjaxFilter = function() {
		$(document).ajaxSend(function(event, request, settings) {
			XPLOOK_CORE.processAjax(settings, request);
		});
	};
	this.sucessAjaxXplook = function (result, status)
	{
		if(xplook.splinner & xpSplinner!= undefined){
			xpSplinner.hide();
		}
		xplook.print(result);
		$('#text_area_console').append(result);
	};
};

var XPLOOK_CORE = new xplook.core();


{
	xplook.print = function(xplook_packet) {
		if (xplook.outputMesages) {
			xplook.console("//");
			console.log(xplook_packet);
			xplook.console("//");
		}
	};
	xplook.processPacket= function(xplook_packet, sucessFunction) {
		if (XPLOOK_CORE.validateXplookPacket(xplook_packet)) {
			XPLOOK_CORE.postToXplookServer(xplook_packet,undefined, sucessFunction);
		} else {
			this.core.error('XplookError');
		}

	};
	xplook.console = function(data) {
		if (xplook.outputMesages) {
			console.log('//XplookRender says:' + data);
			$('#text_area_console').append('<p>XplookRender says:' + data + '</p>');
		}
	};
}


xplook.render = {};

xplook.render.splinner = function(){
	this.show = function(){
		if(xplook.template!=undefined){
			$('#xlookModal').load('boxes/w_'+xplook.template+'_splinner.html');
		}else{
			$('#xlookModal').load('boxes/w_xplookDefault_splinner.html');
		}
	};
	this.hide = function(){
		$('#xlookModal').html('');
	};
};




/*
 * ================================ Xplook packet builder ====================================
 */
{
	xplook.builder = {};
	xplook.builder.xplookHeader=function (mode){
		this.idUser={};
		this.mode= mode;
		this.actionType={};
		this.objectType = {};
	};
	xplook.builder.xplookPacketData =function (){
		this.id={};
		this.database={};
		this.collection={};
		this.request=[];
		this.response=[];
	};
	function xplookErrorItem(){
		this.errorCode={};
		this.description={};
		this.errorType={};
		this.parameters=[];
	}
	function xplookPacket(mode){
		this.xplookHeader = new xplook.builder.xplookHeader(mode);
		this.xplookPacketData = new xplook.builder.xplookPacketData() ;
		this.xplookError= [];
	}

	/*
	 * ================================ Xplook utils ====================================
	 */
	{
		xplook.utils = {
				modes: {
					GET: 'GET',
					POST: 'POST',
					UPDATE: 'UPDATE',
					DELETE: 'DELETE'
				}
		};
	};
}
