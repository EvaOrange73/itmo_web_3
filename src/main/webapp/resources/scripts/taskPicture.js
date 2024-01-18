let draw = SVG().addTo('#task-container').size(300, 300);

function svg(r) {
    "use strict";
    draw.clear();
    draw.rect(300, 300).attr({fill: 'white'});
    drawFigure(draw, r);
    drawAxes(draw);
}

function point(x, y) {
    "use strict";
    draw.circle(3).dx(x - 1).dy(y - 1);
}

function drawAxes() {
    "use strict";
    draw.line(150, 0, 150, 300).stroke({color: 'black', width: 1});
    draw.polyline('147, 5 150, 0 153, 5').stroke({color: 'black', width: 1}).fill('none');
    for (let i = 1; i < 10; i++) {
        if (i !== 5) {
            draw.text(function (add) {
                add.tspan(5 - i).dx(155).dy(i * 30);
            });
        }
        draw.line(147, i * 30, 153, i * 30).stroke({color: 'black', width: 1});
    }

    draw.line(0, 150, 300, 150).stroke({color: 'black', width: 1});
    draw.polyline('295, 147 300, 150 295, 153').stroke({color: 'black', width: 1}).fill('none');
    for (let i = 1; i < 10; i++) {
        if (i !== 5) {
            draw.text(function (add) {
                add.tspan(i - 5).dx(i * 30).dy(145);
            });
        }
        draw.line(i * 30, 147, i * 30, 153).stroke({color: 'black', width: 1});
    }
}

function drawFigure(draw, r) {
    "use strict";
    draw.polygon((150 - r / 2 * 30) + ',150 150,150 150,' + (150 + r * 30)).fill('#3399ff');
    draw.path('M150,150 L' + (150 - r * 30) + ',150 A' + (r * 30) + ',' + (r * 30) + ' 0 0,1 150,' + (150 - r * 30) + ' Z').fill('#3399ff');
    draw.rect(r * 30, r * 30).dx(150).dy(150 - r * 30).fill('#3399ff');
}

svg(3);

document.getElementById("form:r").addEventListener("input", e => {
    "use strict";
    let r = Number(e.target.value);
    if (!Number.isNaN(r)) {
        svg(r);
    }
});

document.getElementById("task-container").addEventListener("click", e => {
    "use strict";
    let rect = document.getElementById("task-container").getBoundingClientRect();
    let x = e.clientX - rect.left;
    let y = e.clientY - rect.top;
    point(x, y);
    document.getElementById("form:x").value = (x / 30 - 5).toFixed(2);
    document.getElementById("form:y").value = (5 - y / 30).toFixed(2);
});
