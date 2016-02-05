html {
     head(title: "Html Input")
     script {
            yieldUnescaped """
                function sendIt() {
                    var xhttp = new XMLHttpRequest(); 
                    xhttp.open("POST", "postjson", false); 
                    xhttp.send('{"code": "key","desc":"value"}');
                    document.getElementById("status").innerHTML = xhttp.status;
                    document.getElementById("result").innerHTML = xhttp.responseText;
                }
            """
     }
     body {
        p(id: 'status')
        p(id: 'result')
        input(type: 'button', onclick:'sendIt();', value='Send')
     }
}
