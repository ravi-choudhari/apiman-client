package org.apiman.client.resources;

import org.apiman.client.domain.system.SystemStatus;

public interface ISystemClient {

	void exportData(String download);

	void importData();

	SystemStatus getSystemStatus();

}
