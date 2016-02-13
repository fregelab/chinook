html {
     head(title: "Html Input") {
         link(rel: "stylesheet", type: "text/css", href:"/css/style.css")
     }
     script {
            yieldUnescaped """
                function sendIt() {
                    var xhttp = new XMLHttpRequest(); 
                    xhttp.open("POST", "/json/post", false); 
                    xhttp.send('{"code": "key","desc":"value"}');
                    document.getElementById("status").innerHTML = xhttp.status;
                    document.getElementById("result").innerHTML = xhttp.responseText;
                }
            """
     }
     body {
        p(id: 'status')
        p(id: 'result')
        input(type: 'button', onclick:'sendIt();', value:'Send')
     }
}
