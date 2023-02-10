'use strict'

let stompClient
let username
let filmId
const connect = (event) => {
    username = document.querySelector('#username').value.trim()
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
            message: messageInput.value + filmId,
            film_id: filmId,
            // time: '12345678900000000',
            type: 'CHAT',
            date: new Date(Date.now()).toISOString()

        }

        stompClient.send("/app/chat.send/" + filmId, {}, JSON.stringify(chatMessage))
        messageInput.value = ''
    }
    event.preventDefault();
}

$(document).ready(function() {
    filmId = $('input[name="film_id"]').val()
    connect()
});



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

    if (message.type === 'CONNECT') {
        messageElement.classList.add('event-message')
        message.content = message.user_name + ' connected!'
    } else if (message.type === 'DISCONNECT') {
        messageElement.classList.add('event-message')
        message.content = message.user_name + ' left!'
    } else {
        messageElement.classList.add('chat-message')

        const avatarContainer = document.createElement('div')
        avatarContainer.className = 'img_cont_msg'
        const avatarElement = document.createElement('div')
        avatarElement.className = 'circle user_img_msg'
        const avatarText = document.createTextNode(message.user_name[0])
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.user_name)
        avatarContainer.appendChild(avatarElement)

        messageElement.style['background-color'] = getAvatarColor(message.user_name)

        flexBox.appendChild(avatarContainer)

        const time = document.createElement('span')
        time.className = 'msg_time_send'
        time.innerHTML = message.time
        messageElement.appendChild(time)

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

const loginForm = document.querySelector('#login-form')
loginForm.addEventListener('submit', connect, true)
const messageControls = document.querySelector('#message-controls')
messageControls.addEventListener('submit', sendMessage, true)