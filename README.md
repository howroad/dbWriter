# dbWriter
数据库创建文件的小工具v0.3
#####公司项目中用到很多CURD操作，由于框架的原因，使用的是Ibatis2.3，使用工具经过简单的配置可以生成ibatis文件，dao文件，注释，等相关信息。

V0.2 
###现在只需要更改settings.properties，仍然还在原位置生成代码，
###如果需要测试只需要把autoRunTest=true，执行start后会自动将生成的代码放入工程并自动编译自动执行，注意自动测试只能测试简单的对象（主键是数字并且没有外键引用的）

V0.3
###支持个性化查询如分页查询，支持模版生成代码

V0.4
###增加可视化界面

###数据库一般是设计好的，但也有可能是需要自己创建。
##1.通过Excel创建可以重复执行的建表语句和序列的生成方法。

###0.0配置文件settings.properties

        #工作空间
        PATH=C:/Users/Administrator/Desktop/model/
        #Excel的地址
        EXCEL_PATH=C:/Users/Administrator/Desktop/model/1.xlsx
        #是否从Excel中读取（会生成建表语句与创建序列的语句）
        fromExcel=true
        #是否从数据库中读取（生成CURD）
        fromDatebase=true
        #是否需要指定模块名
        appNo=temp
        #需要从数据库查询的表名
        tablesFromDB=tps_affair,tps_affair_type
        #序列的命名规则 0表示SEQ_TABLE，1表示TABLE_SEQ
        SEQ_DIR=1
        #是否需要处理序列，如果false则生成的SQL中没有类似selectKey之类的东西
        dealSEQ=true
        #自动生成的实现类是否需要加注释（另类要求）
        implCommont=true
        #是否自动执行测试
        autoRunTest=false
        #primary key must be Number!!!#
        #primary key must be Number!!!#
        #primary key must be Number!!!#
        #主键必须是number格式，否则不能运行！

###1.2生成的TAB文件如下
        
        DECLARE
            CNT INTEGER;
        BEGIN
            SELECT COUNT(0) INTO CNT FROM USER_ALL_TABLES
            WHERE TABLE_NAME = UPPER('TPS_R_CLT_AFFTYPE_FLOW');
            IF CNT = 0 THEN
            EXECUTE IMMEDIATE 'CREATE TABLE TPS_R_CLT_AFFTYPE_FLOW(
                ID NUMBER,
                CLTNO VARCHAR2(30),
                AFFAIR_CODE VARCHAR2(20),
                FW_KEY VARCHAR2(20))';
            END IF;
        END;
        /
        COMMENT ON TABLE TPS_R_CLT_AFFTYPE_FLOW IS '单位事物类型工作流关系表';
        /
        COMMENT ON COLUMN TPS_R_CLT_AFFTYPE_FLOW.ID is '关系表主键';
        /
        COMMENT ON COLUMN TPS_R_CLT_AFFTYPE_FLOW.CLTNO is '单位编号';
        /
        COMMENT ON COLUMN TPS_R_CLT_AFFTYPE_FLOW.AFFAIR_CODE is '事物类型编号';
        /
        COMMENT ON COLUMN TPS_R_CLT_AFFTYPE_FLOW.FW_KEY is '流程编号';
        /
        DECLARE
            CNT INTEGER;
        BEGIN
            SELECT COUNT(0) INTO CNT FROM USER_CONSTRAINTS
            WHERE CONSTRAINT_NAME = UPPER('TPS_R_CLT_AFFTYPE_FLOW_PK');
            IF CNT = 0 THEN
            EXECUTE IMMEDIATE 'ALTER TABLE TPS_R_CLT_AFFTYPE_FLOW ADD CONSTRAINT TPS_R_CLT_AFFTYPE_FLOW_PK PRIMARY KEY(ID)';
            END IF;
        END;
        /
        
###1.2生成的SEQ文件如下
        
        DECLARE
            CNT INTEGER;
        BEGIN
            SELECT COUNT(0) INTO CNT FROM USER_SEQUENCES
            WHERE SEQUENCE_NAME = UPPER('TPS_R_CLT_AFFTYPE_FLOW_SEQ');
            IF CNT = 0 THEN
            EXECUTE IMMEDIATE 'CREATE SEQUENCE TPS_R_CLT_AFFTYPE_FLOW_SEQ
                MINVALUE 1
                MAXVALUE 9999999999999999999999999
                START WITH 1
                INCREMENT BY 1
                CACHE 20';
            END IF;
        END;
        /

 ###2.1生成的XML文件如下
              
        <!-- 新增事务申请 -->
        <insert id="saveAffair" parameterClass="com.nstc.temp.model.Affair">
            <selectKey resultClass="java.lang.Integer" keyProperty="id">
                SELECT TPS_AFFAIR_SEQ.NEXTVAL AS id FROM DUAL
            </selectKey>
            INSERT INTO TPS_AFFAIR (
                ID,
                AFFAIR_CODE,
                CLTNO,
                ACT_DATE,
                DESCRIPTION,
                UPFILENAME,
                STATUS,
                APPROVE_PERSON,
                APPROVE_TIME,
                APPROVE_OPINION,
                BIZCASEID,
                CREATE_PERSON,
                CREATE_TIME,
                UPDATE_PERSON,
                UPDATE_TIME
            ) VALUES(
                #id#,
                #affairCode#,
                #cltno#,
                #actDate#,
                #description#,
                #upfilename#,
                #status#,
                #approvePerson#,
                #approveTime#,
                #approveOpinion#,
                #bizcaseid#,
                #createPerson#,
                #createTime#,
                #updatePerson#,
                #updateTime#
            )
        </insert>

        <!-- 删除事务申请 -->
        <delete id="deleteAffairById" parameterClass="java.lang.Integer">
            delete TPS_AFFAIR WHERE ID = #id#
        </delete>

        <!-- 修改事务申请 -->
        <update id="updateAffair" parameterClass="com.nstc.temp.model.Affair">
            UPDATE TPS_AFFAIR SET
            ID = #id#
            <isNotEmpty prepend="," property="affairCode">
                AFFAIR_CODE = #affairCode#
            </isNotEmpty>
            <isNotEmpty prepend="," property="cltno">
                CLTNO = #cltno#
            </isNotEmpty>
            <isNotEmpty prepend="," property="actDate">
                ACT_DATE = #actDate#
            </isNotEmpty>
            <isNotEmpty prepend="," property="description">
                DESCRIPTION = #description#
            </isNotEmpty>
            <isNotEmpty prepend="," property="upfilename">
                UPFILENAME = #upfilename#
            </isNotEmpty>
            <isNotEmpty prepend="," property="status">
                STATUS = #status#
            </isNotEmpty>
            <isNotEmpty prepend="," property="approvePerson">
                APPROVE_PERSON = #approvePerson#
            </isNotEmpty>
            <isNotEmpty prepend="," property="approveTime">
                APPROVE_TIME = #approveTime#
            </isNotEmpty>
            <isNotEmpty prepend="," property="approveOpinion">
                APPROVE_OPINION = #approveOpinion#
            </isNotEmpty>
            <isNotEmpty prepend="," property="bizcaseid">
                BIZCASEID = #bizcaseid#
            </isNotEmpty>
            <isNotEmpty prepend="," property="createPerson">
                CREATE_PERSON = #createPerson#
            </isNotEmpty>
            <isNotEmpty prepend="," property="createTime">
                CREATE_TIME = #createTime#
            </isNotEmpty>
            <isNotEmpty prepend="," property="updatePerson">
                UPDATE_PERSON = #updatePerson#
            </isNotEmpty>
            <isNotEmpty prepend="," property="updateTime">
                UPDATE_TIME = #updateTime#
            </isNotEmpty>
            WHERE ID = #id#
        </update>

        <!-- 查询事务申请 -->
        <select id="getAffairList" parameterClass="com.nstc.temp.model.Affair" resultClass="com.nstc.temp.model.Affair">
            SELECT
                T.ID AS "id",
                T.AFFAIR_CODE AS "affairCode",
                T.CLTNO AS "cltno",
                T.ACT_DATE AS "actDate",
                T.DESCRIPTION AS "description",
                T.UPFILENAME AS "upfilename",
                T.STATUS AS "status",
                T.APPROVE_PERSON AS "approvePerson",
                T.APPROVE_TIME AS "approveTime",
                T.APPROVE_OPINION AS "approveOpinion",
                T.BIZCASEID AS "bizcaseid",
                T.CREATE_PERSON AS "createPerson",
                T.CREATE_TIME AS "createTime",
                T.UPDATE_PERSON AS "updatePerson",
                T.UPDATE_TIME AS "updateTime"
            FROM TPS_AFFAIR T WHERE 1 = 1
            <isNotEmpty property="id" prepend="and">
                T.ID = #id#
            </isNotEmpty>
            <isNotEmpty property="affairCode" prepend="and">
                T.AFFAIR_CODE = #affairCode#
            </isNotEmpty>
            <isNotEmpty property="cltno" prepend="and">
                T.CLTNO = #cltno#
            </isNotEmpty>
            <isNotEmpty property="actDate" prepend="and">
                T.ACT_DATE = #actDate#
            </isNotEmpty>
            <isNotEmpty property="description" prepend="and">
                T.DESCRIPTION = #description#
            </isNotEmpty>
            <isNotEmpty property="upfilename" prepend="and">
                T.UPFILENAME = #upfilename#
            </isNotEmpty>
            <isNotEmpty property="status" prepend="and">
                T.STATUS = #status#
            </isNotEmpty>
            <isNotEmpty property="approvePerson" prepend="and">
                T.APPROVE_PERSON = #approvePerson#
            </isNotEmpty>
            <isNotEmpty property="approveTime" prepend="and">
                T.APPROVE_TIME = #approveTime#
            </isNotEmpty>
            <isNotEmpty property="approveOpinion" prepend="and">
                T.APPROVE_OPINION = #approveOpinion#
            </isNotEmpty>
            <isNotEmpty property="bizcaseid" prepend="and">
                T.BIZCASEID = #bizcaseid#
            </isNotEmpty>
            <isNotEmpty property="createPerson" prepend="and">
                T.CREATE_PERSON = #createPerson#
            </isNotEmpty>
            <isNotEmpty property="createTime" prepend="and">
                T.CREATE_TIME = #createTime#
            </isNotEmpty>
            <isNotEmpty property="updatePerson" prepend="and">
                T.UPDATE_PERSON = #updatePerson#
            </isNotEmpty>
            <isNotEmpty property="updateTime" prepend="and">
                T.UPDATE_TIME = #updateTime#
            </isNotEmpty>
        </select>

###3.0从数据库中生成的CURD文件，会自动生成测试类。

