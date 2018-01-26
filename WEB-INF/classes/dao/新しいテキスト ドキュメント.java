
	public PostBean getNextPost(String pid){
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection cn = null;
		PostBean pb = new PostBean();
		try{
			cn = OracleConnectionManager.getInstance().getConnection();
			String sql = "SELECT users.uusername, p.ppostid, p.uuserid, p.ppostcontent, p.ppostdate "+
						  "FROM   users JOIN (select * from post WHERE ppostid < "+pid+" ORDER BY ppostid DESC) p "+
						  "ON     users.uuserid = p.uuserid where rownum=1";

			st = cn.prepareStatement(sql);
			rs = st.executeQuery();
			
			if(rs.next()!=true){
				throw new SQLException();
			}
			
			pb.setPostID(rs.getString("pPostID"));
			pb.setUserID(rs.getString("uUserID"));
			//pb.setAvatar("");
			pb.setPostContent(rs.getString("pPostContent"));
			//pb.setPostMedia();
			pb.setPostDate(rs.getString("pPostDate"));
			//pb.setReplyCount();
			//pb.setLikeCount();

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return pb;
	}