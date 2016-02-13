html {
     head(title: "Html Input") {
         link(rel: "stylesheet", type: "text/css", href:"/css/style.css")
     }
     body {
         form(action: '/json/html', method: 'POST') {
             label("Code:")
             input(type:'text', name: 'code')
             label("Description")
             input(type:'text', name: 'desc')
             input(type: 'submit')
         }
     }
}
