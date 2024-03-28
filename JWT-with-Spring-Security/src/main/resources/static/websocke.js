$(document).ready(() => {
    let stompClient = null;

    connectWebSocket();

    $("#submit-bid").click(() => {
        let bidAmount = $("#bid-amount").val();
        let bidCarId = $("#bidCarId").val();
        if (bidAmount.trim() !== "") {
            placeBid(bidAmount, bidCarId);
        } else {
            alert("Please enter a valid bid amount.");
        }
    });

    function connectWebSocket() {
        let socket = new SockJS("http://localhost:8080/bids"); // Make sure this URL matches your backend WebSocket endpoint
        stompClient = Stomp.over(socket);
        stompClient.connect({}, (frame) => {
            console.log("Connected to WebSocket server:", frame);
            stompClient.subscribe('/topic/bids', (message) => {
                console.log("Received message:", message);
                // Handle incoming bid updates, if needed
            });
        }, (error) => {
            console.error("Connection error:", error);
        });
    }

    function placeBid(bidAmount, bidCarId) {
        let bidData = {
            userId: 1001,
            dateTime: new Date().toISOString(),
            amount: bidAmount,
            bidCarId: bidCarId
        };
        // Send the bid data to the server via WebSocket
        stompClient.send("/app/placeBid", {}, JSON.stringify(bidData)); // Make sure the destination matches the backend @MessageMapping

        // Handle responses from the server
        stompClient.subscribe('/user/topic/bids', (message) => {
            let response = JSON.parse(message.body);
            if (response.success) {
                alert("Bid placed successfully.");
            } else {
                alert("Error: " + response.errorMessage);
            }
        });

        console.log("Bid placed:", bidData);
    }
});
