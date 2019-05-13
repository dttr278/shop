<%-- 
    Document   : forbidden
    Created on : Apr 28, 2019, 8:31:41 PM
    Author     : DO TAN TRUNG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >

    <head>
        <meta charset="UTF-8">
        <title>403</title>
        <style>
            /* NOTE: The styles were added inline because Prefixfree needs access to your styles and they must be inlined if they are on local disk! */
            @import url("https://fonts.googleapis.com/css?family=Press+Start+2P");
            html, body {
                width: 100%;
                height: 100%;
                margin: 0;
            }

            * {
                font-family: 'Press Start 2P', cursive;
                box-sizing: border-box;
            }

            #app {
                padding: 1rem;
                background: black;
                display: flex;
                height: 100%;
                justify-content: center;
                align-items: center;
                color: #54FE55;
                text-shadow: 0px 0px 10px;
                font-size: 6rem;
                flex-direction: column;
            }
            #app .txt {
                font-size: 1.8rem;
            }

            @keyframes blink {
                0% {
                    opacity: 0;
                }
                49% {
                    opacity: 0;
                }
                50% {
                    opacity: 1;
                }
                100% {
                    opacity: 1;
                }
            }
            .blink {
                animation-name: blink;
                animation-duration: 1s;
                animation-iteration-count: infinite;
            }

        </style>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
    </head>
    <body>
        <div id="app">
            <div>403</div>
            <div class="txt">
                Forbidden<span class="blink">_</span>
            </div>
        </div>
    </body>

</html>

