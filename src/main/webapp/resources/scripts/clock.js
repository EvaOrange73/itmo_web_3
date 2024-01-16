let radius = 200;
let clockRadius = 195;
let numbersRadius = 170;
let hourLength = 80;
let hourWidth = 5;
let minuteLength = 140;
let minuteWidth = 3;
let secondLength = 150;
let secondWidth = 1;

let month = [
    'января',
    'февраля',
    'марта',
    'апреля',
    'мая',
    'июня',
    'июля',
    'августа',
    'сентября',
    'ноября',
    'декабря',
];

let draw = SVG().addTo('#clock').size(radius * 2, radius * 2);

function drawClock() {
    "use strict";
    draw.clear();
    drawClockFace();
    drawArrows();
    drawDate();
}

drawClock();
setInterval(drawClock, 11000);

function drawClockFace() {
    "use strict";
    let circle = draw.circle(clockRadius * 2).fill('white');
    circle.stroke({color: 'black', width: 2});
    circle.move(radius - clockRadius, radius - clockRadius);

    for (let i = 1; i <= 12; i++) {
        let angle = (i - 3) * Math.PI / 6;
        draw.text(function (add) {
            let text = add.tspan(i);
            text.dx(Math.cos(angle) * numbersRadius + radius - 4);
            text.dy(Math.sin(angle) * numbersRadius + radius + 9);
        });
    }
}

function drawArrows() {
    "use strict";
    const now = new Date();
    let hour = now.getHours() % 12;
    let minute = now.getMinutes();
    let second = now.getSeconds();

    let hourAngle = (hour + minute / 60 + second / 360 - 3) * Math.PI / 6;
    drawArrow(hourAngle, hourLength, hourWidth);

    let minuteAngle = (minute + second / 60 - 15) * Math.PI / 30;
    drawArrow(minuteAngle, minuteLength, minuteWidth);

    let secondAngle = (second - 15) * Math.PI / 30;
    drawArrow(secondAngle, secondLength, secondWidth);
}

function drawArrow(angle, length, width) {
    "use strict";
    let arrow = draw.line(radius, radius, radius + Math.cos(angle) * length, radius + Math.sin(angle) * length);
    arrow.stroke({color: 'black', width: width, linecap: 'round'});
}

function drawDate() {
    "use strict";
    let date = new Date();
    date = `${date.getDay()} ${month[date.getMonth()]} ${date.getFullYear()}`;
    draw.text(function (add) {
        let text = add.tspan(date);
        text.dx(radius - 50);
        text.dy(radius + 50);
    });
}