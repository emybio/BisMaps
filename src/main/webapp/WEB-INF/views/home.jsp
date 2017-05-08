<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>BisMaps</title>
	<script src="http://localhost:8090/geoserver/openlayers3/ol.js" type="text/javascript"></script>
</head>
<body>
<div>
<label>Uzunluk :</label>
<input>
<label>Bölge :</label>
<select>
<c:forEach items="${bolgeler}" var="bolge">
	<option value="${bolge.id}">${bolge.ad}</option>
</c:forEach>
</select>
<label>Ilçe :</label>
<select >
<c:forEach items="${ilceler}" var="ilce">
	<option value="${ilce.id}">${ilce.ad}</option>
</c:forEach>
</select>
<label>Mahalle / Köy :</label>
<select>
<c:forEach items="${mahkoyler}" var="mahkoy">
	<option value="${bolge.id}">${bolge.ad}</option>
</c:forEach>
</select>
<label>Cadde / Sokak :</label>
<select>
<c:forEach items="${cadsoklar}" var="cadsok">
	<option value="${cadsok.id}">${cadsok.ad}</option>
</c:forEach>
</select>
<label>Tarih :</label>
<input>
<label>Tür :</label>
<select>
<c:forEach items="${turler}" var="tur">
	<option value="${tur.id}">${tur.ad}</option>
</c:forEach>
</select>
<label>Aciklama :</label>
<input>
<input type="BUTTON" value="Kaydet">
</div>


<br>
<br>
<div id="map" style="position: absolute; height: 100%; width: 100%;">
	<form class="form-inline">
      <label>Geometry type &nbsp;</label>
      <select id="type">
        <option value="Point">Point</option>
        <option value="LineString">LineString</option>
        <option value="Polygon">Polygon</option>
        <option value="Circle">Circle</option>
        <option value="Square">Square</option>
        <option value="Box">Box</option>
        <option value="None">None</option>
      </select>
    </form>
</div>
<script type="text/javascript">
	 var mousePositionControl = new ol.control.MousePosition({
		 className: 'custom-mouse-position',
		 target: document.getElementById('location'),
		 coordinateFormat: ol.coordinate.createStringXY(5),
		 undefinedHTML: '&nbsp;'
	});
	var format = 'image/png';
	var raster = new ol.layer.Tile({
		visible: true,
		source: new ol.source.TileWMS({
			url: 'http://localhost:8090/geoserver/BisMaps/wms',          
			params: {
				'FORMAT': format,
				'VERSION': '1.1.1',
				tiled: false,
				STYLES: '',
				LAYERS: 'BisMaps:map',
				tilesOrigin: 25.668509999999998 + "," + 35.817496999999996
			}
		})
	});
	var source = new ol.source.Vector({wrapX: false});
	var vector = new ol.layer.Vector({
		source: source,
		style: new ol.style.Style({
			fill: new ol.style.Fill({ color: 'rgba(000, 255, 255, 0.2)' }),
			stroke: new ol.style.Stroke({ color: '#aaa555', width: 5 }),
			image: new ol.style.Circle({
				radius: 7,
				fill: new ol.style.Fill({ color: '#ffcc33' })
			})
		})
	});

	var map = new ol.Map({
		layers: [raster, vector],
		controls: ol.control.defaults({ attribution: true }).extend([mousePositionControl]),
		target: 'map',
		view: new ol.View({
			center: ol.proj.transform([35.1833, 38.3833], 'EPSG:4326', 'EPSG:3857'),
			zoom: 7
		})
	});
	
    var view = new ol.View({ center: [0, 0], zoom: 1 });
      
	var wmsSource = new ol.source.TileWMS({
		url: 'http://localhost:8090/geoserver/BisMaps/wms',
		params: {
			'FORMAT': format,
			'VERSION': '1.1.1',
			tiled: false,
			STYLES: '',
			LAYERS: 'BisMaps:map',
			tilesOrigin: 25.668509999999998 + "," + 35.817496999999996
		}
	});
	
	var url;
	
	map.on('singleclick', function(evt) {
		// var coordinates = evt.coordinate; alert(coordinates);
        
		var viewResolution = view.getResolution();
        url = wmsSource.getGetFeatureInfoUrl(evt.coordinate, viewResolution, 'EPSG:4326', {'INFO_FORMAT': 'text/html'});
        
        if (url) { document.getElementById('myText').value = evt.coordinate; }
	});

	/*map.on('pointermove', function(evt) {
		if (evt.dragging) { return; }
		
        var pixel = map.getEventPixel(evt.originalEvent);
        var hit = map.forEachLayerAtPixel(pixel, function() { return true; });
        map.getTargetElement().style.cursor = hit ? 'pointer' : '';
	});*/
      
	var typeSelect = document.getElementById('type');
	var draw;
	
	function addInteraction() {
		var value = typeSelect.value;
		if (value !== 'None') {
			var geometryFunction, maxPoints;
			if (value === 'Square') {
				value = 'Circle';
				geometryFunction = ol.interaction.Draw.createRegularPolygon(4);
			} else if (value === 'Box') {
				value = 'LineString';
				maxPoints = 2;
				geometryFunction = function(coordinates, geometry) {
					if (!geometry) { geometry = new ol.geom.Polygon(null); }
					var start = coordinates[0], end = coordinates[1];
					geometry.setCoordinates([[start, [start[0], end[1]], end, [end[0], start[1]], start]]);
					
					return geometry;
				};
			}
			draw = new ol.interaction.Draw({
				source: source,
				type: value,
				geometryFunction: geometryFunction,
				maxPoints: maxPoints
			});
			map.addInteraction(draw);
		}
	}
	
	typeSelect.onchange = function() {
		map.removeInteraction(draw);
		addInteraction();
	};
	
	addInteraction();
</script> 

</body>
</html>
