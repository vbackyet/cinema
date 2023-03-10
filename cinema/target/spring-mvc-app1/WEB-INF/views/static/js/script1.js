// 'use strict'

let stompClient
let username
let messages
let filmId;
let userId;
let flag = false;

function setCookie(name,value,days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}
function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}
function eraseCookie(name) {
    document.cookie = name +'=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}





const connect = (event) => {

    username = document.querySelector('#username').value.trim()
    messages = document.querySelector('#messages').value.trim()
    filmId = document.querySelector('#film_id').value.trim()

    if (username) {
        const login = document.querySelector('#login')
        login.classList.add('hide')

        const chatPage = document.querySelector('#chat-page')
        chatPage.classList.remove('hide')

        const socket = new SockJS('/chat-example')
        stompClient = Stomp.over(socket)
        stompClient.connect({}, onConnected, onError)
    }
    event.preventDefault()
}


// stompClient.subscribe(`/films/${filmId}/chat/messages`,
//     function(messageOutput) {
//     const message = JSON.parse(messageOutput.body)
//     if (message && message.id > 0 && message.created_at) {
//         showMessage(message)
//     }

const onConnected = () => {
    stompClient.subscribe(`/topic/public/${filmId}`, onMessageReceived) //подписывается на этот канал
    stompClient.send(`/app/chat.newUser/${filmId}`,
        {},
        JSON.stringify({user_name: username, type: 'CONNECT', film_id:filmId})
    )
    const status = document.querySelector('#status')

    status.className = 'hide'
}

const onConnected2 = () => {
    // alert("onConnected2")
    stompClient.subscribe(`/topic/public/${filmId}`, onMessageReceived) //подписывается на этот канал
    stompClient.send(`/app/chat.newUser/${filmId}`,
        {},
        JSON.stringify({
            user_name: null,
            message: null,
            film_id: filmId,
            // time: '12345678900000000',
            user_id: getCookie("chat"),
            type: 'RECONNECT',
            date: new Date(Date.now()).toISOString()

        })
    )
    const status = document.querySelector('#status')

    status.className = 'hide'

}


const onError = (error) => {
    const status = document.querySelector('#status')
    status.innerHTML = 'Could not find the connection you were looking for. Move along. Or, Refresh the page!'
    status.style.color = 'red'
}

const sendMessage = (event) => {
    const messageInput = document.querySelector('#message')
    const messageContent = messageInput.value.trim()
    if (messageContent && stompClient) {
        const chatMessage = {
            user_name: username,
            message: messageInput.value,
            film_id: filmId,
            // time: '12345678900000000',
            user_id: getCookie('chat'),
            type: 'CHAT',
            date: new Date(Date.now()).toISOString()

        }

        stompClient.send("/app/chat.send/" + filmId, {}, JSON.stringify(chatMessage))
        messageInput.value = ''
    }
    event.preventDefault();
}


const imagesave = (event) => {
    alert(name)

}



// $(document).ready(function() {
//     filmId = $('input[name="film_id"]').val()
//     connect()
// });



const printAllMessages = (message, chatCard) => {
    let i = 0;
    const obj = null;
    // const obj = JSON.parse(messages);
    $.getJSON('http://localhost:8080/messages/search?filmName=' + filmId, function(data) {
        Object.keys(data).forEach(function(key)
        {
            // document.write(data[key]["sender"])
            let sender = data[key]["sender"]["username"]
            let message = data[key]["content"]
            let time1 = data[key]["time"]
            let image = data[key]["sender"]["user_avatar"]
            const flexBox = document.createElement('div')
            flexBox.className = 'd-flex justify-content-end mb-4'
            chatCard.appendChild(flexBox)

            const messageElement = document.createElement('div')
            messageElement.className = 'msg_container_send'

            flexBox.appendChild(messageElement)
            messageElement.classList.add('chat-message')

            // alert(image)
            // if (!image) {
            //     // картинка
            //     const avatarContainer = document.createElement('div')
            //     avatarContainer.className = 'img_cont_msg'
            //     const avatarElement = document.createElement('div')
            //     avatarElement.className = 'circle user_img_msg'
            //     const avatarText = document.createTextNode(sender[0])
            //     avatarElement.appendChild(avatarText);
            //     avatarElement.style['background-color'] = getAvatarColor(sender)
            //     avatarContainer.appendChild(avatarElement)
            // }
            // else {

                ///////////////////////
                ////////// новая катинка //////////

                const avatarContainer = document.createElement('div')
                avatarContainer.className = 'img_cont_msg'
                var img = document.createElement("img");
                img.src = "/UrlForImage/" + "avatar/" + image;
                img.height = 50;
                img.width = 50;
                avatarContainer.appendChild(img)
                // var src = document.getElementById("header");
                // src.appendChild(img);
            // }


            /////////////////////////////////////
            messageElement.style['background-color'] = getAvatarColor(sender)

            flexBox.appendChild(avatarContainer)

            const time = document.createElement('span')
            time.className = 'msg_time_send'
            time.innerHTML = time1
            messageElement.appendChild(time)
            messageElement.innerHTML = message

            const chat = document.querySelector('#chat')
            chat.appendChild(flexBox)
            chat.scrollTop = chat.scrollHeight
        })
    });
}


const onMessageReceived = (payload) => {

    const message = JSON.parse(payload.body);

    const chatCard = document.createElement('div')
    chatCard.className = 'card-body'

    const flexBox = document.createElement('div')
    flexBox.className = 'd-flex justify-content-end mb-4'
    chatCard.appendChild(flexBox)

    const messageElement = document.createElement('div')
    messageElement.className = 'msg_container_send'

    flexBox.appendChild(messageElement)
    // alert(message.type + "  THE TYPE")
    if (message.type == 'CONNECT') {
        messageElement.classList.add('event-message')
        userId = message.user_id
        setCookie('chat',userId,7);
        const form = document.querySelector("#some-random-id")
        form.setAttribute("action", "/chat/save/" + userId + "/" + filmId + "/")
        // form.setAttribute("th:action", "@{/chat/save/3}")
 // message.content = message.user_name + ' connected!'
    } else if (message.type == 'DISCONNECT') {

        messageElement.classList.add('event-message')
        message.content = message.user_name + ' left!'
    } else if (message.type == 'RECONNECT')
    {
        // alert("RECONNECT")
        flag = false;
        username = message.user_name
        messageElement.classList.add('event-message')
        userId = message.user_id
        const form = document.querySelector("#some-random-id")
        form.setAttribute("action", "/chat/save/" + userId + "/" + filmId + "/")
    }
    else {
        messageElement.classList.add('chat-message')

        let image = message.image;


        ///катинка
        // if (image)
        // {
            const avatarContainer = document.createElement('div')
            avatarContainer.className = 'img_cont_msg'
            var img = document.createElement("img");
            img.src = "/UrlForImage/avatar/" + image;
            img.height = 50;
            img.width = 50;
            avatarContainer.appendChild(img)
        // }
        //     else
        // {
        //     const avatarContainer = document.createElement('div')
        //     avatarContainer.className = 'img_cont_msg'
        //     const avatarElement = document.createElement('div')
        //     avatarElement.className = 'circle user_img_msg'
        //     const avatarText = document.createTextNode(message.user_name[0]);
        //     avatarElement.appendChild(avatarText);
        //     avatarElement.style['background-color'] = getAvatarColor(message.user_name);
        //     avatarContainer.appendChild(avatarElement);
        // }




        messageElement.style['background-color'] = getAvatarColor(message.user_name)

        flexBox.appendChild(avatarContainer)

        const time = document.createElement('span')
        time.className = 'msg_time_send'
        time.innerHTML = message.time
        messageElement.appendChild(time)

    }

    if ((!flag)) {
        printAllMessages(message, chatCard)
        flag = true
        return
    }

    messageElement.innerHTML = message.message

    const chat = document.querySelector('#chat')
    chat.appendChild(flexBox)
    chat.scrollTop = chat.scrollHeight
}

const hashCode = (str) => {
    let hash = 0
    for (let i = 0; i < str.length; i++) {
        hash = str.charCodeAt(i) + ((hash << 5) - hash)
    }
    return hash
}


const getAvatarColor = (messageSender) => {
    const colours = ['#2196F3', '#32c787', '#1BC6B4', '#A1B4C4']
    const index = Math.abs(hashCode(messageSender) % colours.length)
    return colours[index]
}

var x = getCookie('chat');

// alert(x + " my Cook")
if (x) {
    flag = false;
    userId = x;
    filmId = document.querySelector('#film_id').value.trim()
    const login = document.querySelector('#login')
    login.classList.add('hide')

    const chatPage = document.querySelector('#chat-page')
    chatPage.classList.remove('hide')
    const socket = new SockJS('/chat-example')
    stompClient = Stomp.over(socket)
    // alert(username)
    stompClient.connect({}, onConnected2, onError)

    const messageControls = document.querySelector('#message-controls')
    messageControls.addEventListener('submit', sendMessage, true)
    // document.getElementById('user_id').innerText = "3";
    // const imageControls = document.querySelector('#image-controls')
    // imageControls.addEventListener('submit', imagesave, true)
}
else
{
    const login = document.querySelector('#login')
    login.classList.remove('hide')

    const loginForm = document.querySelector('#login-form')
    loginForm.addEventListener('submit', connect, true)
    const messageControls = document.querySelector('#message-controls')
    messageControls.addEventListener('submit', sendMessage, true)
    //
    // const imageControls = document.querySelector('#image-controls')
    // imageControls.addEventListener('submit', imagesave, true)
}
