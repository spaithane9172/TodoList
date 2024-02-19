let element=document.getElementById("toast");

function onOff(){
    element.classList.add("hidden");
}
if(element!=null){
    setTimeout(()=>{
        if(!element.classList.contains("hidden")){
            element.classList.add("hidden");
        }
    },5000);
}