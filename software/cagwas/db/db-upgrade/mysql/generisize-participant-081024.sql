/*L
  Copyright SAIC.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/cagwas/LICENSE.txt for details.
L*/

update study_participant set participant_did = Concat('XYZ',substr(participant_did, 7))
where participant_did like 'CG%';
commit;

