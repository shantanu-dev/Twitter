

// initialize the map
var map = L.map('map', {
    Center: [28.61346, 77.3877],
    zoom: 13,
    inertia:true,
    keyboard:true
});

// load a tile layer


L.tileLayer('https://api.mapbox.com/styles/v1/shantanu24/cirwzoown002kgznm670mk27j/tiles/256/{z}/{x}/{y}?access_token=pk.eyJ1Ijoic2hhbnRhbnUyNCIsImEiOiJjaXJ3eXNibWwwMDMzMnRvZm1pZmpsMnc4In0.jfP7RXQ50x9WAfSqP8cX1Q', {
    attribution: 'Map Designed by Shantanu',
    maxZoom: 18,
    keyboard:true,
    accessToken: 'pk.eyJ1Ijoic2hhbnRhbnUyNCIsImEiOiJjaXJ3eXNibWwwMDMzMnRvZm1pZmpsMnc4In0.jfP7RXQ50x9WAfSqP8cX1Q'
}).addTo(map);

var marker = L.marker([27.23509, 77.98096]).addTo(map);


var circle = L.circle([26.84368, 83.1665], 10000, {
    color: 'blue',
    fillColor: '#f03',
    fillOpacity: 0.5
}).addTo(map);

var polygon = L.polygon([
    [13.15438, 80.00244],
    [11.0706, 76.88232],
    [13.06878, 77.36572]
]).addTo(map);
marker.bindPopup("<b>Hello world!</b><br>u r here</b>").openPopup();
circle.bindPopup("I am a circle.");
polygon.bindPopup("I am a polygon.");

var popup = L.popup()
    .setLatLng([17.39258, 78.22266])
    .setContent("I am a standalone popup.")
    .openOn(map);

var popup = L.popup();

function onMapClick(e)   {
    popup
        .setLatLng(e.latlng)
        .setContent("You clicked the map at " + e.latlng.toString())
        .openOn(map);
}

map.on('click', onMapClick);
var bounds = [[54.559322, -5.767822], [56.1210604, -3.021240]];

// create an orange rectangle
//L.rectangle(bounds, {color: "#ff7800", weight: 1}).addTo(map);

// zoom the map to the rectangle bounds
map.fitBounds(bounds);

//var polyline = L.polyline(map.latlng, {color: 'red'}).addTo(map);

// zoom the map to the polyline
//map.fitBounds(polyline.getBounds());
//var point = L.point(200, 300);
//map.panBy([200, 300]);
//map.panBy(L.point(200, 300));


L.Routing.control({
    waypoints: [
        L.latLng(28.55542, 77.26776),
        L.latLng(33.67864, 73.00415)
    ]
}).addTo(map);


function onEachFeature(feature, layer) {
    // does this feature have a property named popupContent?
    if (feature.properties && feature.properties.popupContent) {
        layer.bindPopup(feature.properties.popupContent);
    }
}

var geojsonFeature = {
    "type": "Feature",
    "properties": {
        "name": "Coors Field",
        "amenity": "Baseball Stadium",
        "popupContent": "This is where the jitendra play!"
    },
    "geometry": {
        "type": "Point",
        "coordinates": [51.5, -0.11]
    }
};
51.5, -0.09
L.geoJson(geojsonFeature, {
    onEachFeature: onEachFeature
}).addTo(map);





var states = [{
    "type": "Feature",
    "properties": {"party": "Republican"},
    "geometry": {
        "type": "Polygon",
        "coordinates": [[
            [-104.05, 48.99],
            [-97.22,  48.98],
            [-96.58,  45.94],
            [-104.03, 45.94],
            [-104.05, 48.99]
        ]]
    }
}, {
    "type": "Feature",
    "properties": {"party": "Democrat"},
    "geometry": {
        "type": "Polygon",
        "coordinates": [[
            [-109.05, 41.00],
            [-102.06, 40.99],
            [-102.03, 36.99],
            [-109.04, 36.99],
            [-109.05, 41.00]
        ]]
    }
}];

L.geoJson(states, {
    style: function(feature) {
        switch (feature.properties.party) {
            case 'Republican': return {color: "#ff0000"};
            case 'Democrat':   return {color: "#0000ff"};
        }
    }
}).addTo(map);



