html {
     head(title: "Html Input")
     body {
         form(action: 'posthtml', method: 'POST') {
             input(name: 'code')
             input(name: 'desc')
             input(type: 'submit')
         }
     }
}
