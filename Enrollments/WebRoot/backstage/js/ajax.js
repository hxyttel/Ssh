var httpRequest=null;
		function createXHR(){		
				if(window.XMLHttpRequest){ 
					httpRequest = new XMLHttpRequest();
				}else if(window.ActiveXObject){
					try{
						httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
					}catch(e){
						try {
							httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
						}catch(e){
							httpRequest = null;
						}
					}		
				}	   
				if(!httpRequest){   
					alert("");		
				}  
		}

		function sendRequest(url,params,method,handler){
			createXHR();
			if(!httpRequest){
				return false;
			}	
			httpRequest.onreadystatechange = handler;
			httpRequest.open(method,url+ '?' + params,true);
			if(method == "GET"){		
				httpRequest.send(null);
			}
			if(method == "POST"){
				httpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
				httpRequest.send(params);  
			}	  
		}