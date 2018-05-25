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
package fr.paris.lutece.plugins.workflow.modules.directoryfile.service.task;

import fr.paris.lutece.plugins.directory.business.File;
import fr.paris.lutece.plugins.directory.service.file.DirectoryFileService;
import fr.paris.lutece.plugins.workflow.modules.directoryfile.business.task.config.TaskGlobalFilePurgeConfig;
import fr.paris.lutece.plugins.workflowcore.business.resource.ResourceHistory;
import fr.paris.lutece.plugins.workflowcore.service.config.TaskConfigService;
import fr.paris.lutece.plugins.workflowcore.service.resource.ResourceHistoryService;
import fr.paris.lutece.plugins.workflowcore.service.task.SimpleTask;
import fr.paris.lutece.portal.service.i18n.I18nService;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Task for purging files associated to given ressource id
 */
public class TaskGlobalFilePurge extends SimpleTask
{
    private static final String MESSAGE_TASK_FILE_PURGE_TITLE = "module.workflow.directoryfile.task.filePurge.title";
    @Inject
    private DirectoryFileService _directoryFileService;
    @Inject
    @Resource( name = "workflow-directoryfile.taskGlobalFilePurgeConfigService" )
    private TaskConfigService _taskConfigService;
    @Inject
    private ResourceHistoryService _resourceHistoryService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void processTask( int nIdResourceHistory, HttpServletRequest request, Locale locale )
    {

        ResourceHistory resourceHistory = _resourceHistoryService.findByPrimaryKey( nIdResourceHistory );

        // Get all the file associated to given ressource id
        List<File> listFiles = _directoryFileService.getAssociatedFiles( resourceHistory.getIdResource( ) );
        TaskGlobalFilePurgeConfig config = _taskConfigService.findByPrimaryKey( getId( ) );

        for ( File file : listFiles )
        {
            if ( config.isInstantPurge( ) )
            {
                _directoryFileService.purge( file );
            }
            else
                if ( config.getDaysBeforePurge( ) > 0 )
                {
                    Timestamp tNow = new Timestamp( System.currentTimeMillis( ) );
                    Calendar cal = Calendar.getInstance( );
                    cal.setTime( tNow );
                    cal.add( Calendar.DAY_OF_WEEK, config.getDaysBeforePurge( ) );
                    Timestamp tAfter = new Timestamp( cal.getTime( ).getTime( ) );
                    file.setDateExpiration( tAfter );
                    _directoryFileService.update( file );
                }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle( Locale locale )
    {
        return I18nService.getLocalizedString( MESSAGE_TASK_FILE_PURGE_TITLE, locale );
    }
}
