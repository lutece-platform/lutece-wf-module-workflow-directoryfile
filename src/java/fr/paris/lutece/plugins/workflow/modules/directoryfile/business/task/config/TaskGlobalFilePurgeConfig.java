/*
 * Copyright (c) 2002-2018, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.workflow.modules.directoryfile.business.task.config;

import fr.paris.lutece.plugins.workflowcore.business.config.TaskConfig;

public class TaskGlobalFilePurgeConfig extends TaskConfig
{
    private boolean _bInstantPurge;
    private int _nDaysBeforePurge;

    /**
     * Get the instantPurge boolean
     * 
     * @return true if instant purge, false otherwise
     */
    public boolean isInstantPurge( )
    {
        return _bInstantPurge;
    }

    /**
     * Set the instant purge boolean
     * 
     * @param bInstantPurge
     *            the instant purge boolean
     */
    public void setInstantPurge( boolean bInstantPurge )
    {
        _bInstantPurge = bInstantPurge;
    }

    /**
     * Get the nb days before purge
     * 
     * @return the nb of days before purge
     */
    public int getDaysBeforePurge( )
    {
        return _nDaysBeforePurge;
    }

    /**
     * Set the nb days before purge
     * 
     * @param nDaysBeforePurge
     *            the nb days before purge
     */
    public void setDaysBeforePurge( int nDaysBeforePurge )
    {
        _nDaysBeforePurge = nDaysBeforePurge;
    }

}
