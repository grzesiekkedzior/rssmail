function sendEmail() {
        const data =
        {
            //fromPerson : "jhon@company.com",
            mailAddress : emailAddress()
        }

        saveEmail(data);
        //document.getElementById("mail_input").setAttribute(rss());
}

function saveRssAddress() {
    const data = {
        rssAddress : rss()
    }

    saveRss(data);
}

function getDataRss() {
    const URL = 'https://rss-mail.azurewebsites.net/rssmail/mailcreator';
    var xhr = new XMLHttpRequest();
    xhr.open("GET", URL, true);
    xhr.send();
    xhr.onload = function(e) {
        document.getElementById("mail_input").innerHTML = xhr.responseText;
    }
}

function emailAddress() {
    return document.getElementById("address_label").value;
}

function rss() {
    return document.getElementById("rss_label").value
}

function saveEmail(data) {
    const URL = 'https://rss-mail.azurewebsites.net/rssmail/managedmail';
    var xhr = new XMLHttpRequest();
    xhr.open("POST", URL, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(data));
}

function saveRss(data) {
    const URL = 'https://rss-mail.azurewebsites.net/rssmail/managedrss'
    var xhr = new XMLHttpRequest();
    
    
    xhr.open("POST", URL, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    //xhr.setRequestHeader('Access-Control-Allow-Origin', 'rss-mail.azurewebsites.net');
    //xhr.setRequestHeader('Access-Control-Allow-Credentials', 'true');
    xhr.send(JSON.stringify(data));
}

function getData() {
    const URL = 'https://rss-mail.azurewebsites.net/rssmail/mailcreator'
    var xhr = new XMLHttpRequest();
    xhr.open("GET", URL, true);
    var response = JSON.parse(xhr.responseText);
    document.getElementById("mail_input").innerHTML = "test";
}

function sendSpam() {
    const URL = 'https://rss-mail.azurewebsites.net/emails'
    var xhr = new XMLHttpRequest();
    xhr.open("POST", URL, true);
    xhr.send();
}

