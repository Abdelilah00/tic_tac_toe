let bot = new RiveScript();
let user_input = document.getElementById('user_input');
let chat_logs = document.getElementById('chatlogs');

// Load an individual file.
bot.loadFile("brain/begin.rive", loading_brain_done, loading_brain_error);
bot.loadFile("brain/test.rive", loading_brain_done, loading_brain_error);
bot.loadFile("brain/star.rive", loading_brain_done, loading_brain_error);

function loading_brain_done () {
    console.log('loading Done');
    bot.sortReplies();
}
function loading_brain_error () {
    console.log('error loading')
}

document.body.onkeydown = function ClickEnter(e){
    if(e.keyCode === 13){
        SendMsg();
    }
};
document.getElementById("Send").onclick = function (){
    SendMsg();
};

/**
 * @return {boolean}
 */
function SendMsg() {

    ///////////////test input-user
    if(user_input.value === ""){
        return false;
    }
    if(user_input.value === "\n"){
        user_input.value = '';
        return false;
    }

    //////////set new element chatBox Client
    createBox("chat Client", user_input.value);

    ///////////////set new element chatBox Bot
    createBox("chat Bot", bot.reply("local_user", user_input.value));


    //////////update page
    let objDiv = document.getElementById("chatlogs");
    objDiv.scrollTop = objDiv.scrollHeight;
    user_input.value = '';
}
function createBox(for_, msg) {

    //////create element
    let element = document.createElement('div');
    let element_div = document.createElement('div');
    let element_p = document.createElement('p');

    //////set attribute for element
    element.setAttribute("class", for_);
    element_div.setAttribute("class", "user-photo");
    element_p.setAttribute("class", "chat-message");

    element_p.textContent = msg;

    ////// append element to parent
    element.appendChild(element_div);
    element.appendChild(element_p);

    /////append parent to log chat
    chat_logs.appendChild(element);
}
