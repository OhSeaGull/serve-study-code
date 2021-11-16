
//建立一个本地服务器：localhost:8081/
const http = require("http")

const hostname = 'localhost'
const port = 8081

const server = http.createServer((req,res) => {

	res.statusCode = 200

	res.setHeader('Content-Type', 'text/plain')

	res.end('Hello World\n')
});

server.listen(port, hostname, () => {
	console.log(`Server running at http://${hostname}:${port}/`)
});




	//开启一个监听事件，每次HTTP请求都会触发这个事件
//	http.createServer((req,res) => {
	//把编码设置成utf-8
//	res.write('<head><meta charset="utf-8"/></head>')
	//设置响应体信息
//	res.write("subleise build a server on nodeJS!")
	//结束事件
//	res.end()

//}).listen(8081)	//设置监听端口

//console.log(`running the server...`) 


