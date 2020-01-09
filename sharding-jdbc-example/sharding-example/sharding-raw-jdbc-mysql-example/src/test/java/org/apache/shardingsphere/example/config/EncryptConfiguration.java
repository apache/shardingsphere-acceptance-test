package org.apache.shardingsphere.example.config;

import org.apache.shardingsphere.encrypt.api.EncryptColumnRuleConfiguration;
import org.apache.shardingsphere.encrypt.api.EncryptRuleConfiguration;
import org.apache.shardingsphere.encrypt.api.EncryptTableRuleConfiguration;
import org.apache.shardingsphere.encrypt.api.EncryptorRuleConfiguration;
import org.apache.shardingsphere.example.core.api.DataSourceUtil;
import org.apache.shardingsphere.example.core.api.DatabaseType;
import org.apache.shardingsphere.shardingjdbc.api.EncryptDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EncryptConfiguration implements ExampleConfiguration {

    @Override
    public DataSource getDataSource() throws SQLException {
        return EncryptDataSourceFactory.createDataSource(DataSourceUtil.createDataSource("demo_ds", DatabaseType.MYSQL), getEncryptRuleConfiguration(), new Properties());
    }

    private EncryptRuleConfiguration getEncryptRuleConfiguration() {
        Properties props = new Properties();
        props.setProperty("aes.key.value", "123456");
        EncryptorRuleConfiguration encryptorAES = new EncryptorRuleConfiguration("AES", props);
        EncryptorRuleConfiguration encryptorMD5 = new EncryptorRuleConfiguration("MD5",new Properties());
        Map<String, EncryptColumnRuleConfiguration> columns = new HashMap<>();
        EncryptColumnRuleConfiguration columnUserName = new EncryptColumnRuleConfiguration("user_name", "user_name_cipher", "", "aes");
        EncryptColumnRuleConfiguration columnPwd = new EncryptColumnRuleConfiguration("pwd_plain", "pwd_cipher", "", "md5");
        columns.put("user_name",columnUserName);
        columns.put("pwd",columnPwd);
        EncryptTableRuleConfiguration tableConfig = new EncryptTableRuleConfiguration(columns);
        EncryptRuleConfiguration result = new EncryptRuleConfiguration();
        result.getEncryptors().put("aes", encryptorAES);
        result.getEncryptors().put("md5",encryptorMD5);
        result.getTables().put("t_user", tableConfig);
        return result;
    }
}
