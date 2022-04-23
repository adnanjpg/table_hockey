# The Documentation

## Server
the entry point is `App.Java`. here we run our `main` method which created an instance of a `Server` and spins it up.

the `Server` opens a listener on a seperate thread to new connections and creates a new `ServerClient` for each new client connecting.

the `ServerClient` needs to call methods on the `Server`, for that purpose I did not prefer static variables and methods,
instead I've created a `ServerClientCallbacks` interface that handles all these callbacks.
`ServerClientCallbacks` has a callback called `requestQueue`. this will be called when the client requests a match.
if the queue is empty, `requestQueue` puts the client in the queue.
if there is another client in the queue, `requestQueue` will initilize a new game and add both clients to it, and add the game
to the ongoing games list.

`GameModel` initializes its own `ServerSocket` and thus a seperate connection, this approach seperates conecrns and is more managable.

[uncompleted] once `GameModel` is initialized it will create a `BallModel`, which holds the current position of the ball (hockey puck).
while `GameModel` is running it will continuosly stream the position of the ball to the clients. and also listen to the position of each 
player, calculate game state and stream it to the players.

## Client
the entry point is `MainMenu.java` which is a `JFrame Form`. this form initilizes a connection form, and after connected it will show a 
queue request form with a name field. after establishing a connection, we initialize a `Client`, which also has callbacks.

- `setRequestedMatchStatus`: after requesting a match, we will recieve a success or failure response. this callback is responsible
for handling the ui accordingly.
- `disconnect`: on some cases we have to disconnect. this callback is responsible for handling the ui accordingly.
- `startGame`: after finding a pair, this callback will be called, and it initializes a `GameScreen` (details below) and makes it visible

### `GameScreen`
[uncompleted] in this screen, we initialize a game connection to the server and recieve `BallModel` info, and send `PlayerModel` info,
which indicates our player's current state.

## The Communication
for the communication, we're always sending a `SocketMessage`. it is considered a Request or Response, depending on the payload.
and it has 4 properties:
- `title`: to specify the type of the message, for example `request_match`, which goes as a request from the client to the server, and as a 
response back. (more on this below)
- `data`: indicates the payload of the message. used only in request messages.
- `params`: indicates the payload of the message. used only in request messages.
- `status`: indicates the status of the response, http style. (200 for success, 404 for not found, etc.)
