You have been assigned to review a software project which is having issues delivering reliable releases of its software.
The attached code is the codebase as copied from their subversion repository. You have a meeting at which you will be 
asked to present your ideas about what is wrong with the software and how it can be improved. Note that the main obejective is
not neccessarily to find bugs, or to redesign the code, but to educate the client developers such that they understand where
they are going wrong and how they can improve their ability to develop reliable, maintainable and extensible code.

Note that the software uses a custom persistence service developed by another group over which you have no direct influence.
This is simply made available to the developers via a jar.  The API is given below.

public interface DbService {
	public Object loadFromDb(String connectionDetails, String sql, Class type) throws SQLException;
	
	public Object saveToDatabase(Object data, String connectionDetails) throws SQLException;
	
	public void delete(String id, Class type, String connectionDetails) throws SQLException;
}
