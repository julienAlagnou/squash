function renderActions(actions, actionContainerElmt) {

	// clean column action
	actionContainerElmt.html('');

	if (actions != null) {
		$.each(actions, function(i, action) {
			switch (action.type) {
			case "RESOURCE":
				var button = $('<input class="action-button" type="button" value="' + action.name + '"/>');
				button.data("url", action.url);
				button.data("method", action.method);
				actionContainerElmt.append(button);
				break;
			case "PAGE":
				var shipLink = $('<a href="' + action.url + '">' + action.name + '</a>');
				actionContainerElmt.append(shipLink);
				break;
			default:
				console.error("Unknow action type " + action.type + ".");
			}
		});
	}

}
