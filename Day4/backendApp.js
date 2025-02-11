const express=require('express')
const app=express()

const port=2225

app.use(express.json())

let data=[]

let idx=1;

app.post("/orders",(req,res)=>{
    const newEntry={
        id: idx++,
        customerName: req.body.customerName,
        totalPrice: req.body.totalPrice
    }

    data.push(newEntry);
    res.status(201).send(newEntry)
})

app.get("/orders",(req,res)=>{
    res.status(200).send(data)
})

app.get("/orders/:id",(req,res)=>{
    const orderid=parseInt(req.params.id);
    const filteredObj=data.filter((x)=>x.id===orderid);
 
    if(filteredObj.length==0){
        res.status(404).send()
    }
    else{
        res.status(200).send(filteredObj[0])
    }
})

app.put("/orders/:id",(req,res)=>{
    const orderid=parseInt(req.params.id);
    const filteredObj=data.filter((x)=>x.id!==orderid);

    if(filteredObj.length==data.length){
        res.status(404).send()
    }
    else{
        filteredObj.push({
            id:orderid,
            customerName: req.body.customerName,
            totalPrice: req.body.totalPrice
        })
        data=filteredObj
        res.status(200).send({id:orderid,customerName:req.body.customerName,totalPrice:req.body.totalPrice})
    }
})

app.delete("/orders/:id",(req,res)=>{
    const orderid=parseInt(req.params.id);
    const filteredObj=data.filter((x)=>x.id!==orderid);

    if(filteredObj.length==data.length){
        res.status(404).send()
    }
    else{
        data=filteredObj
        res.status(200).send()
    }
})

app.listen(port,()=>{
    console.log("server running at port "+port)
})