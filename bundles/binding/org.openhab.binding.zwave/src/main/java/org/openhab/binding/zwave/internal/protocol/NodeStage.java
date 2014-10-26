/**
 * Copyright (c) 2010-2014, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.zwave.internal.protocol;

import java.util.HashMap;
import java.util.Map;

/**
 * Node Stage Enumeration. Represents the state the node
 * is in.
 * @author Brian Crosby
 * @since 1.3.0
 */
public enum NodeStage {
	EMPTYNODE(0, "Empty New Node"),
	PROTOINFO(1, "Protocol Information"),
	PING(2, "Ping Node"),
	WAKEUP(3, "Wake Up"),
	DETAILS(4, "Node Information"),
	MANUFACTURER(5, "Manufacture Name and Product Identification"),
	VERSION(6, "Node Version"),
	ENDPOINTS(7, "Command Class Endpoints"),
	STATIC_VALUES(8, "Static Information"),
	// States below are not restored from the configuration files
	SESSION_START(9, "Restore Marker"),
	ASSOCIATIONS(10, "Association Information"),
	NEIGHBORS(11, "Node Neighbor Information"),
	SESSION(12, "Infrequently Changed Information"),
	DYNAMIC_VALUES(13, "Frequently Changed Information"),
	CONFIG(14, "Parameter Information"),
	DONE(15, "Node Complete"),
	INIT(16, "Node Not Started"),
	DEAD(17, "Node Dead"),
	FAILED(18,"Node Failed");
	
	private int stage;
	private String label;
	
	/**
	 * A mapping between the integer code and its corresponding
	 * Node Stage to facilitate lookup by code.
	 */
	private static Map<Integer, NodeStage> codeToNodeStageMapping;
	
	private NodeStage (int s, String l) {
		stage = s;
		label = l;
	}
	
	private static void initMapping() {
		codeToNodeStageMapping = new HashMap<Integer, NodeStage>();
		for (NodeStage s : values()) {
			codeToNodeStageMapping.put(s.stage, s);
		}
	}
	
	/**
	 * Get the stage protocol number.
	 * @return number
	 */
	public int getStage() {
		return this.stage;
	}
	
	/**
	 * Get the stage label
	 * @return label
	 */
	public String getLabel() {
		return this.label;
	}
	
	/**
	 * Lookup function based on the command class code.
	 * Returns null if there is no command class with code i
	 * @param i the code to lookup
	 * @return enumeration value of the command class.
	 */
	public static NodeStage getNodeStage(int i) {
		if (codeToNodeStageMapping == null) {
			initMapping();
		}
		
		return codeToNodeStageMapping.get(i);
	}
	
	/**
	 * Return the next stage after the current stage
	 * @return the next stage
	 */
	public NodeStage getNextStage() {
		for (NodeStage s : values()) {
			if(s.stage == this.stage + 1) {
				return s;
			}
		}
		
		return null;
	}
}

