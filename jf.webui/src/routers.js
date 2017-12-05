export default [
    /**
     * 公用路由
     */
    
    {name:'login',path:'/login',noAuthor:true,
        component:(resolve)=>require(['./views/login.vue'],resolve)
    },
    {name:'main',path:'/index',
        component:(resolve)=>require(['./views/main.vue'],resolve),
        children:[
            {name:'home',path: '/',
                component: (resolve) => require(['./views/welcome.vue'], resolve)
            },
            {name:'index',path: '/welcome',
                component: (resolve) => require(['./views/welcome.vue'], resolve)
            },
            /**
             * 参数配置
            */
            {name:'property',path: '/jf/config/property/listProperty',
                component: (resolve) => require(['./views/jf/config/property/listProperty.vue'], resolve)
            },
            {name:'sitemap',path: '/jf/config/sitemap/siteMapMgt',
                component: (resolve) => require(['./views/jf/config/sitemap/siteMapMgt.vue'], resolve)
            },
            {name:'lookup',path: '/jf/config/lookup/lookupMgt',
                component: (resolve) => require(['./views/jf/config/lookup/lookupMgt.vue'], resolve)
            },
            {name:'i18n',path: '/jf/config/i18n/listI18n',
                component: (resolve) => require(['./views/jf/config/i18n/listI18n.vue'], resolve)
            },
            {name:'htmlarea',path: '/jf/config/htmlarea/listHtmlarea',
                component: (resolve) => require(['./views/jf/config/htmlarea/listHtmlarea.vue'], resolve)
            },
            {name:'excelTemplate',path: '/jf/config/excel/excelTemplate',
                component: (resolve) => require(['./views/jf/config/excel/excelTemplate.vue'], resolve)
            },
            {name:'personalized',path: '/jf/config/personalized/listPersonalized',
                component: (resolve) => require(['./views/jf/config/personalized/listPersonalized.vue'], resolve)
            },
            {name:'attachment',path: '/jf/config/attachment/listAttachment',
                component: (resolve) => require(['./views/jf/config/attachment/listAttachment.vue'], resolve)
            },

            /**
             * 账户管理
             */
            {name:'user',path: '/jf/security/user/listUser',
                component: (resolve) => require(['./views/jf/security/user/listUser.vue'], resolve)
            },
            {name:'role',path: '/jf/security/role/listRole',
                component: (resolve) => require(['./views/jf/security/role/listRole.vue'], resolve)
            },
            {name:'group',path: '/jf/security/group/listGroup',
                component: (resolve) => require(['./views/jf/security/group/listGroup.vue'], resolve)
            },
            /*{name:'permission',path: '/jf/security/permission/listPermission',
                component: (resolve) => require(['./views/jf/security/permission/listPermission.vue'], resolve)
            },*/

            /**
             * 系统监控
             */
            {name:'logMonitor',path: '/jf/monitor/log/listAuditLog',
                component: (resolve) => require(['./views/jf/monitor/log/listAuditLog.vue'], resolve)
            },
            {name:'serviceMonitor',path: '/jf/monitor/service/serviceTab',
                component: (resolve) => require(['./views/jf/monitor/service/serviceTab.vue'], resolve)
            },
            /*{name:'databaseMonitor',path: '/jf/monitor/database/databaseTab',
                component: (resolve) => require(['./views/jf/monitor/database/databaseTab.vue'], resolve)
            },*/
            {name:'importMonitor',path: '/jf/config/excel/listImportTask',
                component: (resolve) => require(['./views/jf/config/excel/listImportTask.vue'], resolve)
            },
            {name:'exportMonitor',path: '/jf/config/excel/listExportTask',
                component: (resolve) => require(['./views/jf/config/excel/listExportTask.vue'], resolve)
            },

            /**
             * 工作空间
             */
            {name:'myPermission',path: '/jf/workspace/myPermission',
                component: (resolve) => require(['./views/jf/workspace/myPermission.vue'], resolve)
            },
            /*{name:'myApply',path: '/jf/workspace/myApply',
                component: (resolve) => require(['./views/jf/workspace/myApply.vue'], resolve)
            },
            {name:'myTodo',path: '/jf/workspace/myToDo',
                component: (resolve) => require(['./viewsjf/workspace/myToDo.vue'], resolve)
            },
            {name:'myWorkflow',path: '/jf/workspace/myWorkflow',
                component: (resolve) => require(['./views/jf/workspace/myWorkflow.vue'], resolve)
            },*/
            {name:'myImport',path: '/jf/config/excel/myImportTask',
                component: (resolve) => require(['./views/jf/config/excel/myImportTask.vue'], resolve)
            },
            {name:'myExport',path: '/jf/config/excel/myExportTask',
                component: (resolve) => require(['./views/jf/config/excel/myExportTask.vue'], resolve)
            }   
        ]
    }
];