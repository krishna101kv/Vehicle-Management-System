/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.kanand4.web;

import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import java.util.logging.Logger;

/**
 *
 * @author kris
 */
public class JSFPhaseListener implements PhaseListener {

    private static final Logger LOG = Logger.getLogger(JSFPhaseListener.class.getName());

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    @Override
    public void beforePhase(PhaseEvent event) {

        // RESTORE_VIEW is the first phase
        if (event.getPhaseId() == PhaseId.RESTORE_VIEW) {
            LOG.info(" ================================ A NEW JSF REQUEST IS STARTING ================================");
        }

        LOG.info("BEFORE THE JSF PHASE =========================================== >>>>> " + event.getPhaseId().toString());
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        LOG.info("AFTER THE JSF PHASE =========================================== >>>>> " + event.getPhaseId().toString());

        // RENDER_RESPONSE is the last phase
        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            LOG.info(" ================================ THE JSF REQUEST HAS COMPLETED ================================");
        }

    }

}