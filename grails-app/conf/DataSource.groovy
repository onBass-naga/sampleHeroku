dataSource {
    pooled = true
    logSql = true
    driverClassName = "org.postgresql.Driver"
    dialect = org.hibernate.dialect.PostgreSQLDialect
    username = "postgres"
    password = "postgres"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:postgresql://192.168.159.132:5432/heroku"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:postgresql://192.168.159.132:5432/heroku"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:postgresql://hoge.amazonaws.com:5432/hoge"
            username = "enterUserName"
            password = "enterPassword"
            ssl = true
            sslfactory = org.postgresql.ssl.NonValidatingFactory
            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}
