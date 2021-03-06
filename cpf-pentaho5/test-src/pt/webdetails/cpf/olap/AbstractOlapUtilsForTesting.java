package pt.webdetails.cpf.olap;

import mondrian.olap.Connection;
import mondrian.olap.Member;
import mondrian.rolap.RolapMember;
import mondrian.rolap.RolapResult;
import org.pentaho.platform.api.engine.ICacheManager;
import org.pentaho.platform.plugin.action.mondrian.catalog.IMondrianCatalogService;
import org.pentaho.platform.plugin.action.mondrian.catalog.MondrianCatalog;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class AbstractOlapUtilsForTesting extends AbstractOlapUtils {

  private Connection mockedConnection;


  public void setConnection( Connection connection ) {
    this.mockedConnection = connection;
  }

  public Connection getConnection() {
    return this.mockedConnection;
  }

  @Override
  protected String getJndiFromCatalog( MondrianCatalog catalog ) {
    return "testJndi";
  }

  @Override
  protected DataSource getDatasourceImpl( String dataSourceName ) throws Exception {
    return null;
  }

  protected IMondrianCatalogService getMondrianCatalogService() {
    return new MondrianCatalogServiceMock();
  }

  protected ICacheManager getCacheManager() {
    return null;
  }

  @Override
  public List<MondrianCatalog> getMondrianCatalogs() {
    return mondrianCatalogService.listCatalogs( userSession, true );
  }

  protected Connection getMdxConnectionFromConnectionString( String connectStr ) {
    return this.mockedConnection;
  }

  protected Connection getMdxConnection( String catalog ) {
    return this.mockedConnection;
  }


  protected List<RolapMember> getMeasuresMembersFromResult( RolapResult result ) {
    return createMeasuresMembers();
  }

  public static List<RolapMember> createMeasuresMembers() {
    List<RolapMember> rolapMembers = new ArrayList<RolapMember>();
    rolapMembers.add( new RolapMemberMock( "all", "member '[All]'", "member with type all", Member.MemberType.ALL ) );
    rolapMembers.add(
        new RolapMemberMock( "formula", "member '[Formula]'", "member with type formula", Member.MemberType.FORMULA ) );
    rolapMembers.add(
        new RolapMemberMock( "measure", "member '[Measure]'", "member with type measure", Member.MemberType.MEASURE ) );
    rolapMembers
        .add( new RolapMemberMock( "null", "member '[Null]'", "member with type null", Member.MemberType.NULL ) );
    rolapMembers.add(
        new RolapMemberMock( "regular", "member '[Regular]'", "member with type regular", Member.MemberType.REGULAR ) );
    rolapMembers.add(
        new RolapMemberMock( "unknown", "member '[Unknown]'", "member with type unknown", Member.MemberType.UNKNOWN ) );
    return rolapMembers;
  }

}
