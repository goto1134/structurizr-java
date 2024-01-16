package com.structurizr.inspection.model;

import com.structurizr.Workspace;
import com.structurizr.inspection.Severity;
import com.structurizr.inspection.Violation;
import com.structurizr.model.InfrastructureNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class InfrastructureNodeDescriptionInspectionTests {

    @Test
    public void run_WithoutDescription() {
        Workspace workspace = new Workspace("Name", "Description");
        InfrastructureNode infrastructureNode = workspace.getModel().addDeploymentNode("Deployment Node")
                .addInfrastructureNode("Name");

        Violation violation = new InfrastructureNodeDescriptionInspection(workspace).run(infrastructureNode);
        Assertions.assertEquals(Severity.ERROR, violation.getSeverity());
        assertEquals("structurizr.inspection.model.infrastructurenode.description", violation.getType());
        assertEquals("Add a description to the infrastructure node named \"Name\".", violation.getMessage());
    }

    @Test
    public void run_WithDescription() {
        Workspace workspace = new Workspace("Name", "Description");
        InfrastructureNode infrastructureNode = workspace.getModel().addDeploymentNode("Deployment Node")
                .addInfrastructureNode("Name", "Description", "Technology");

        Violation violation = new InfrastructureNodeDescriptionInspection(workspace).run(infrastructureNode);
        assertNull(violation);
    }

}
