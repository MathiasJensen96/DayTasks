import entities.Member;
import persistence.Database;
import persistence.MemberMapper;
import persistence.RegistrationMapper;

import java.util.List;

public class Main {

    private final static String USER = "root";
    private final static String PASSWORD = "Cervelo2011";
    private final static String URL = "jdbc:mysql://localhost:3306/sportsclub?serverTimezone=CET&useSSL=false";

    public static void main(String[] args) {

        Database db = new Database(USER, PASSWORD, URL);
        MemberMapper memberMapper = new MemberMapper(db);
        RegistrationMapper registrationMapper = new RegistrationMapper(db);
        List<Member> members = memberMapper.getAllMembers();

        showMembers(members);
        showMemberById(memberMapper, 13);
        int newMemberId = insertMember(memberMapper);
        deleteMember(newMemberId, memberMapper);
        showMembers(members);
        updateMember(13, memberMapper);

        System.out.println("*****Participants on Teams*****");
        memberMapper.participantsOnTeams();
        System.out.println("*****Participants on Sports*****");
        memberMapper.participantsOnSport();
        System.out.println("*****Number pr. Gender*****");
        memberMapper.numberPrGender();
        System.out.println("*****Income for all Teams*****");
        memberMapper.totalSum();
        System.out.println("*****Income for each Team*****");
        memberMapper.sumForEachTeam();
        System.out.println("*****AVG Income for each Team*****");
        memberMapper.avgForEachTeam();

        registrationMapper.addToTeam(13, "ten02");

        System.out.println("**********************");
        registrationMapper.getAllRegistrations();



    }

    private static void deleteMember(int memberId, MemberMapper memberMapper) {
        if (memberMapper.deleteMember(memberId)){
            System.out.println("Member with id = " + memberId + " is removed from DB");
        }
    }

    private static int insertMember(MemberMapper memberMapper) {
        Member m1 = new Member("Ole Olsen", "Banegade 2", 3700, "Rønne", "m", 1967);
        Member m2 = memberMapper.insertMember(m1);
        showMemberById(memberMapper, m2.getMemberId());
        return m2.getMemberId();
    }

    private static void updateMember(int memberId, MemberMapper memberMapper) {
        Member m1 = memberMapper.getMemberById(memberId);
        m1.setYear(1970);
        if(memberMapper.updateMember(m1)){
            showMemberById(memberMapper, memberId);
        }
    }

    private static void showMemberById(MemberMapper memberMapper, int memberId) {
        System.out.println("***** Vis medlem nr. 13: *******");
        System.out.println(memberMapper.getMemberById(memberId).toString());
    }

    private static void showMembers(List<Member> members) {
        System.out.println("***** Vis alle medlemmer *******");
        for (Member member : members) {
            System.out.println(member.toString());
        }
    }


}
